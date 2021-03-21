package org.lcm.response.result.spring.boot.autoconfig;

import org.lcm.response.result.config.HandlerMethodReturnValueHandlerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({HandlerMethodReturnValueHandlerConfig.class})
public class ResponseResultAutoConfigure {
    private static final Logger logger = LoggerFactory.getLogger(ResponseResultAutoConfigure.class);
    {
        logger.info("Load ResponseResultAutoConfigure");
    }
}
