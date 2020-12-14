package com.taikang.oredpack.apisign.config;

import com.taikang.oredpack.apisign.handle.SignCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/12/711:00
 */
@Configuration
public class WebSignHandleConfig extends WebMvcConfigurationSupport{
    @Autowired
    private SignCheckInterceptor signCheckInterceptor;
    //配置拦截器
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        System.out.println("*******************************");
       registry.addInterceptor(signCheckInterceptor).addPathPatterns("/signapi/**");//signapi的请求加入拦截器
       super.addInterceptors(registry);
    }
}
