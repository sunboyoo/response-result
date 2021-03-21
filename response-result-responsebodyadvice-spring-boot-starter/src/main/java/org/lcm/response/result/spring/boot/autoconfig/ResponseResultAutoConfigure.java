package org.lcm.response.result.spring.boot.autoconfig;

import org.lcm.response.result.webmvcconfigurer.ResponseBodyAdviceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 使用 @AutoConfigureBefore(WebMvcAutoConfiguration.class)
 * 解决返回 String 类型的异常问题。
 */


@Configuration
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@Import({ResponseBodyAdviceConfig.class})
public class ResponseResultAutoConfigure{
    private static final Logger logger = LoggerFactory.getLogger(ResponseResultAutoConfigure.class);
    {
        logger.info("Load ResponseResultAutoConfigure");
    }
}
