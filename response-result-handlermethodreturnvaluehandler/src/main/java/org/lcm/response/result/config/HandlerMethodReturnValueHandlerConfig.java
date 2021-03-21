package org.lcm.response.result.config;

import org.lcm.response.result.handlermethodreturnvaluehandler.ResponseResultHandlerMethodReturnValueHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class HandlerMethodReturnValueHandlerConfig implements InitializingBean {
    @Autowired
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;


    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> customReturnValueHandlers = new ArrayList<>();

        assert returnValueHandlers != null;
        for (HandlerMethodReturnValueHandler handler : returnValueHandlers){
            if (handler instanceof RequestResponseBodyMethodProcessor){
                // 把自定义的 handler 放在 RequestResponseBodyMethodProcessor 的前面
                // 自定义的 handler 能处理的则优先处理
                customReturnValueHandlers.add(new ResponseResultHandlerMethodReturnValueHandler((RequestResponseBodyMethodProcessor)handler));
                // 自定义的 handler 无法处理的，再检查 RequestResponseBodyMethodProcessor 能否处理
                customReturnValueHandlers.add(handler);
            } else {
                customReturnValueHandlers.add(handler);
            }
        }

        requestMappingHandlerAdapter.setReturnValueHandlers(Collections.unmodifiableList(customReturnValueHandlers));
    }
}
