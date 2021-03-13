package org.lcm.response.result.spring.boot.autoconfig;

import org.lcm.response.result.webmvcconfigurer.ResponseBodyAdviceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 此 boot-starter 依然存在返回String类型的异常问题。
 */

@Configuration
@Import({ResponseBodyAdviceConfig.class})
public class ResponseResultAutoConfigure{
    private static final Logger logger = LoggerFactory.getLogger(ResponseResultAutoConfigure.class);
    {
        logger.info("Load ResponseResultAutoConfigure");
    }
}
