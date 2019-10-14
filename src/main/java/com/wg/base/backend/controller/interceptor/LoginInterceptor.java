package com.wg.base.backend.controller.interceptor;

import com.wg.base.backend.common.exception.LogicException;
import com.wg.base.backend.common.result.ResultMessage;
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
        String url = request.getRequestURL().toString();
        LOGGER.info("request getContextPath is --" +request.getContextPath());
        LOGGER.info("request getServletPath is --" +request.getServletPath());
        LOGGER.info("request url is --" +url);
        if(url.contains("swagger")||url.contains("/error")) {
            LOGGER.info("Interceptor return true...");
            return true;
        }
        String token = request.getHeader("access_token");
        if(token == null){
            throw new LogicException(ResultMessage.TOKEN_EMPTY_ERROR);
        }
        boolean result = TokenUtils.verify(token);
        if (!result) {
            throw new LogicException(ResultMessage.TOKEN_INVALID_ERROR);
        }
        LOGGER.info("access_token is right..." );
        return true;
    }

}
