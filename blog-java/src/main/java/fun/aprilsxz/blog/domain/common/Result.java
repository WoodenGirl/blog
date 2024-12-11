package fun.aprilsxz.blog.domain.common;

import fun.aprilsxz.blog.exception.CommonException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "后端返回结果")
public class Result<T> {
    @ApiModelProperty("状态码")
    private int code;
    @ApiModelProperty("错误信息")
    private String msg;
    @ApiModelProperty("返回数据")
    private T data;

    public static Result<Void> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(200, "OK", data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> error(CommonException e){return new Result<>(e.getCode(),e.getMessage(),null);}

    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean success(){
        return code == 200;
    }
}
