package org.lcm.response.result;

import org.lcm.response.result.annotation.ResponseResult;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ResponseResultConfiguration.class)
public @interface EnableResponseResult {
}
