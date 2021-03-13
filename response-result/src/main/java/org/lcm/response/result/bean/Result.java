package org.lcm.response.result.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private Object data;

    public Result(HttpStatus httpStatus, Object data){
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.data = data;
    }

    // 静态方法返回 success
    public static Result success(){
        return Result.success(null);
    }

    // 静态方法返回 success
    public static Result success(Object data){
        return new Result(HttpStatus.OK, data);
    }

    // 静态方法返回 success, 通用方法。
    public static Result success(Integer code, String message, Object data){
        return new Result(code, message, data);
    }

    // 静态方法返回 failure
    public static Result failure(){
        return Result.failure(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
    // 静态方法返回 failure
    public static Result failure(HttpStatus httpStatus){
        return Result.failure(httpStatus, null);
    }

    // 静态方法返回 failure
    public static Result failure(HttpStatus httpStatus, Object data){
        return new Result(httpStatus, data);
    }

    // 静态方法返回 failure, 通用方法。
    public static Result failure(Integer code, String message, Object data){
        return new Result(code, message, data);
    }
}
