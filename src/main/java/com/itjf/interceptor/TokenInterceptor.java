package com.itjf.interceptor;

import com.itjf.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 令牌校验拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1. 获取请求路径
//        String requestURI = request.getRequestURI(); // /login
//        //2. 判断请求路径是否是登录请求，如果包含/login。说明是登录操作，放行
//        if(requestURI.contains("/login")){
//            log.info("登录操作,放行");
//            return true;
//        }
        //3.获取请求头中的token
        String token = request.getHeader("token");
        //4.判断token是否存在，如果不存在，说明用户没有登录，返回错误信息401状态码
        if(token == null || token.isEmpty()){
            log.info("令牌为空,用户未登录，请先登录");
            response.setStatus(401);
            return false;
        }
        //5.如果存在，调用jwt工具类进行解析，如果解析失败，返回错误信息401状态码
        try{
            JwtUtils.parseToken(token);
        }catch (Exception e){
            log.info("令牌非法，响应401");
            response.setStatus(401);
            return false;
        }
        //6.如果解析成功，说明登录成功，返回登录成功信息200状态码
        log.info("令牌合法，放行");
        return true;
    }
}
