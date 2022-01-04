package com.Important.config;

import com.Important.entity.User;
import com.Important.mapper.UserMapper;
import com.Important.utils.JwtUserLogin;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;
import java.io.IOException;

@Slf4j
@Component
public class HandLerConfig implements HandlerInterceptor{
    @Resource
    private JwtUserLogin jwtUserLogin;
    @Resource
    private UserMapper  userMapper;

    /**
     * 拦截器：接口请求前执行该方法，如果为true则往后继续执行
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        try{
            String token = request.getHeader("token");
            if (token.isEmpty()){
                return  false;
            }
            String userName = jwtUserLogin.getUserName(token);
            log.info("用户名"+userName);
            User username = userMapper.selectOne(new QueryWrapper<User>().eq("username", userName));
            if (StringUtils.isEmpty(userName)){
                return  false;
            }
            System.out.println("来自主机ip为"+request.getRemoteAddr()+"的"+request.getRequestURI()+"请求过来了");
            return true;
        }catch ( Exception e){
            log.info(""+e);
            onLoginFail(response);
            return false;
        }
//        System.out.println("来自主机ip为"+request.getRemoteAddr()+"的"+request.getRequestURI()+"请求过来了");
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write("无效令牌,请重新登录重试");
    }

}
