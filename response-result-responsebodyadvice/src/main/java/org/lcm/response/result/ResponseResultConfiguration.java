package org.lcm.response.result;

import org.lcm.response.result.webmvcconfigurer.ResponseBodyAdviceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ResponseBodyAdviceConfig.class)
public class ResponseResultConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ResponseResultConfiguration.class);
    {
        logger.info("Load ResponseResultConfiguration");
    }
}
