package com.wg.base.backend.controller.interceptor;

import com.wg.base.backend.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);


    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("request getContextPath is --" +request.getContextPath());
        LOGGER.info("request getServletPath is --" +request.getServletPath());
        String token = request.getHeader("access_token");
        if (token != null) {
            boolean result = TokenUtils.verify(token);
            if (result) {
                return true;
            }
        }
        return false;
    }

}
