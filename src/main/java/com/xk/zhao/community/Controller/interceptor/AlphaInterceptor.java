package com.xk.zhao.community.Controller.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */

@Component
public class AlphaInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AlphaInterceptor.class);
    //Controller之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("preHandle:" + handler.toString());
        System.out.println("preHandle:" + handler.toString());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    //调用完Controller之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("postHandle:" + handler.toString());
        System.out.println("postHandle:" + handler.toString());
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }


    //模板之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("afterHandle:" + handler.toString());
        System.out.println("afterHandle" + handler.toString());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
