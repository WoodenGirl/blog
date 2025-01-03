package fun.aprilsxz.blog.advice;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.exception.BadRequestException;
import fun.aprilsxz.blog.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

    @ExceptionHandler(CommonException.class)
    public Object handleBadRequestException(CommonException e) {
        log.error("自定义异常 -> {} , 异常原因：{}  ",e.getClass().getName(), e.getMessage());
        log.debug("", e);
        return processResponse(e);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Object handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> messages = constraintViolations.stream().map(new Function<ConstraintViolation<?>, String>() {
            @Override
            public String apply(ConstraintViolation<?> constraintViolation) {
                return constraintViolation.getMessage();
            }
        }).collect(Collectors.toList());
        String message = CollUtil.join(messages,";");
        log.error("请求参数校验异常 -> {}", message);
        log.debug("", e);
        return processResponse(new BadRequestException(message));
    }

    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e) {
        List<String> messages = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        String message = CollectionUtil.join(messages, ";");
        log.error("请求参数绑定异常 ->BindException， {}", message);
        log.debug("", e);
        return processResponse(new BadRequestException(message));
    }

    @ExceptionHandler(Exception.class)
    public Object handleRuntimeException(Exception e) {
        log.error("其他异常 :  ", e);
        return processResponse(new CommonException("服务器内部异常", 500));
    }

    private ResponseEntity<Result<Void>> processResponse(CommonException e){
        return ResponseEntity.status(e.getCode()).body(Result.error(e));
    }
}
