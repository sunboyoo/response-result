package org.lcm.response.result.responsebodyadvice;

import org.lcm.response.result.annotation.ResponseResult;
import org.lcm.response.result.bean.Result;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// @ResponseBodyAdvice 需要搭配 @ControllerAdvice 才能生效
@ControllerAdvice
public class ResponseResultAdvice implements ResponseBodyAdvice<Object> {

    // 是否需要重写返回值
    // true - 重写返回值。
    // false - 不重写返回值
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        ResponseResult responseResultAnnotation;

        // 检查Controller类上是否有注解 @ResponseResult
        responseResultAnnotation = methodParameter.getMethodAnnotation(ResponseResult.class);
        if (responseResultAnnotation != null){
            return true;
        }

        // 检查Controller方法上是否有注解 @ResponseResult
        responseResultAnnotation = methodParameter.getDeclaringClass().getAnnotation(ResponseResult.class);
        return responseResultAnnotation != null;
    }

    // 重写返回值 - 包装成 Result
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // 如果Controller方法的返回值已经是Result类型的数据，则不再进行包装。
        // 如果Controller方法的返回值是 Resource 类型的资源数据，则不进行包装。
        if (body instanceof Result || body instanceof Resource){
            return body;
        }


        // 当Controller方法的返回值类型是String的时候，Spring会自动分配 StringMessageConverter，如果此时转换成 Result类型，StringMessageConverter会报错。
        // java.lang.ClassCastException: org.lcm.response.result.bean.Result cannot be cast to java.lang.String
//        if (body instanceof String){
//            try {
//
//                return new ObjectMapper().writeValueAsString(Result.success(body));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//                return body;
//            }
//        }

        return Result.success(body);
    }
}
