package hello.config;//package com.taikang.hello.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @Description
// * @Author:itw_zhangjian04
// * @Date： 2020/11/2613:52
// */
//@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//    //安全拦截机制
//    @Override
//    protected void configure(HttpSecurity httpSecurity)throws Exception{
//        httpSecurity.csrf().disable().authorizeRequests()
//                .antMatchers("/test/**").authenticated()  //所有/test/**必须需要认证通过
//                .anyRequest().permitAll();  //其他请求不需要认证就可以访问
//    }
//}
