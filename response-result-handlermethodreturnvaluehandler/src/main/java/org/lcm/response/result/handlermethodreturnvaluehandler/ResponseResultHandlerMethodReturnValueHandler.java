package org.lcm.response.result.handlermethodreturnvaluehandler;

import org.lcm.response.result.annotation.ResponseResult;
import org.lcm.response.result.bean.Result;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * 简单代理模式 - 或者包装模式
 * 使用自定义的 HandlerMethodReturnValueHandler 代理 RequestResponseBodyMethodProcessor
 * 它们都实现了同样的接口 HandlerMethodReturnValueHandler
 * https://mp.weixin.qq.com/s/8aMz07rOF5LuclnBaI_p5g
 */


public class ResponseResultHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    // 被代理的对象
    private final RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;

    public ResponseResultHandlerMethodReturnValueHandler(RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor) {
        this.requestResponseBodyMethodProcessor = requestResponseBodyMethodProcessor;
    }

    /**
     * RequestResponseBodyMethodProcessor 源码
     * supportsReturnType：从这个方法中可以看到，这里支持有 @ResponseBody 注解的接口
     * handleReturnValue：这是具体的处理逻辑，首先 mavContainer 中设置 requestHandled 属性为 true，表示这里处理完成后就完了，
     * 以后不用再去找视图了，然后分别获取 inputMessage 和 outputMessage，调用 writeWithMessageConverters 方法进行输出，
     * writeWithMessageConverters 方法是在父类中定义的方法，这个方法比较长，核心逻辑就是调用确定输出数据、确定 MediaType，
     * 然后通过 HttpMessageConverter 将 JSON 数据写出去即可
     */

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        // 被代理对象执行检查
        boolean hasResponseBodyAnnotation = this.requestResponseBodyMethodProcessor.supportsReturnType(returnType);

        // 检查是否在类上或者方法上包含注解 @ResponseResult
        boolean hasResponseResultAnnotation =
                AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseResult.class) ||
                        returnType.hasMethodAnnotation(ResponseResult.class);

        // 可以有两种包装逻辑
        // (1) 自动把所有的@ResponseBody的返回值包装成Result对象。优点：无需注解、无代码侵入。缺点：不可以设置哪些Controller的返回不包装
        // (2) 在需要包装成Result对象的Controller上加注解@ResponseResult。优点：可以自定义包装或者不包装、看到注解就知道包装了。缺点：需要注解、有代码侵入
        // 参考Spring的@ResponseBody需要自己写上去，所以决定使用第(2)中方式。
        return hasResponseBodyAnnotation && hasResponseResultAnnotation;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        // 将 returnValue 包装为 Result 对象
        returnValue = Result.success(returnValue);

        // 被代理对象执行操作
        this.requestResponseBodyMethodProcessor.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}
