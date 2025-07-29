package com.itjf.filter;

import com.itjf.utils.CurrentHolder;
import com.itjf.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1. 获取请求路径
        String requestURI = request.getRequestURI(); // /login
        //2. 判断请求路径是否是登录请求，如果包含/login。说明是登录操作，放行
        if(requestURI.contains("/login")){
            log.info("登录操作,放行");
            filterChain.doFilter(request,response);
            return;
        }
        //3.获取请求头中的token
        String token = request.getHeader("token");
        //4.判断token是否存在，如果不存在，说明用户没有登录，返回错误信息401状态码
        if(token == null || token.isEmpty()){
            log.info("令牌为空,用户未登录，请先登录");
            response.setStatus(401);
            return;
        }
        //5.如果存在，调用jwt工具类进行解析，如果解析失败，返回错误信息401状态码
        try{
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录员工id为：{}，将其存入ThreadLocal",empId);
        }catch (Exception e){
            log.info("令牌非法，响应401");
            response.setStatus(401);
            return;
        }
        //6.如果解析成功，说明登录成功，返回登录成功信息200状态码
        log.info("令牌合法，放行");
        filterChain.doFilter(request,response);
        //删除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}
