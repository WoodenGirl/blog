package fun.aprilsxz.blog.advice;

import cn.hutool.core.collection.CollectionUtil;
import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.exception.BadRequestException;
import fun.aprilsxz.blog.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.WebUtils;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

//    @ExceptionHandler(DbException.class)
//    public Object handleDbException(DbException e) {
//        log.error("mysql数据库操作异常 -> ", e);
//        return processResponse(e);
//    }

    @ExceptionHandler(CommonException.class)
    public Object handleBadRequestException(CommonException e) {
        log.error("自定义异常 -> {} , 异常原因：{}  ",e.getClass().getName(), e.getMessage());
        log.debug("", e);
        return processResponse(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors()
                .stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("|"));
        log.error("请求参数校验异常 -> {}", msg);
        log.debug("", e);
        return processResponse(new BadRequestException(msg));
    }

    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e) {
        List<String> messages = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        String message = CollectionUtil.join(messages, ";");
        log.error("请求参数绑定异常 ->BindException， {}", message);
        log.debug("", e);
        return processResponse(new BadRequestException(message));
    }

//    @ExceptionHandler(NestedServletException.class)
//    public Object handleNestedServletException(NestedServletException e) {
//        log.error("参数异常 -> NestedServletException，{}", e.getMessage());
//        log.debug("", e);
//        return processResponse(new BadRequestException("请求参数处理异常"));
//    }

//    @ExceptionHandler(Exception.class)
//    public Object handleRuntimeException(Exception e) {
//        log.error("其他异常 uri : {} -> ", WebUtils.getRequest().getRequestURI(), e);
//        return processResponse(new CommonException("服务器内部异常", 500));
//    }

    private ResponseEntity<Result<Void>> processResponse(CommonException e){
        return ResponseEntity.status(e.getCode()).body(Result.error(e));
    }
}
