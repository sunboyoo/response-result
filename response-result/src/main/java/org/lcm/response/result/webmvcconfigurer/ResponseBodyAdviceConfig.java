package org.lcm.response.result.webmvcconfigurer;

import org.lcm.response.result.responsebodyadvice.ResponseResultAdvice;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 把 ResponseBodyAdvice 注册入容器
 * 提升 JacksonMessageConverter 的优先级
 */
@EnableWebMvc
@Configuration
@Import(ResponseResultAdvice.class)
public class ResponseBodyAdviceConfig implements WebMvcConfigurer {

    /**
     * 情景：在Controller方法的返回值为String类型的时候，用Result包装，报错
     * 报错：java.lang.ClassCastException: org.lcm.response.result.bean.Result cannot be cast to java.lang.String
     * 原因：
     *      因为在所有的 HttpMessageConverter 实例集合中，
     *      StringHttpMessageConverter 要比其它的 Converter 排得靠前一些。
     *      我们需要将处理 Object 类型的 HttpMessageConverter 放得靠前一些，这可以在 Configuration 类中完成
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }
}
