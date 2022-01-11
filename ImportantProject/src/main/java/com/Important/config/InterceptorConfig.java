package com.Important.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Value("${file.updatePath}")
    private String locationsImg;


    @Value("${file.viewPath}")
    private String imgServiceUrl;



    @Resource
    private HandLerConfig   handLerConfig;
    //解决跨域问题

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//设置允许跨域的路径
                .allowedOrigins("*")//设置允许跨域请求的域名
                .allowCredentials(true)//是否允许证书 不再默认开启
                .allowedMethods("GET","POST","DELETE","PUT")
                .maxAge(3600);
    }

    //添加拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handLerConfig)//注册自定义拦截器
                .addPathPatterns("/**")//拦截的请求路径
                .excludePathPatterns("/error","/User/login","/swagger-ui.html/**","/swagger-resources/**","/swagger-resources","/doc.html","/webjars/**","/User/UserRegistration","/User/VerificationCode","/fileData/**","/%E6%97%A0%E6%A0%87%E9%A2%98.png")//排除的请求路径
                .excludePathPatterns("/static/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/"+imgServiceUrl+"/**").addResourceLocations("file:" + locationsImg);
    }

}

