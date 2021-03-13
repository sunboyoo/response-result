package org.lcm.response.result.annotation;

import java.lang.annotation.*;

/**
 * 注解 @ResponseResult 用来标记 Controller 方法的返回值，是否需要包装为 Result 类型
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
}
