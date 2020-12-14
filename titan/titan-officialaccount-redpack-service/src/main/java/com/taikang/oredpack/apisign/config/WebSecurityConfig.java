package com.taikang.oredpack.apisign.config;//package com.taikang.hello.apisign.config;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebSecurityConfig extends WebMvcConfigurerAdapter {
//
//    /**
//     * 处理POST方法中参数多次获取的问题
//     *
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean MyFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new VersionCheckFilter());
//        //不设置registration.setOrder，则使用默认级别：Integer.MAX_VALUE，数字越大，级别越低
//        return registration;
//    }
//
//    @Bean
//    public AuthHandlerInterceptor restAuthHandlerInterceptor(){
//        return new AuthHandlerInterceptor();
//    }
//    /**
//     * 配置拦截器
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration interceptor = registry.addInterceptor(restAuthHandlerInterceptor());
//        interceptor.addPathPatterns("/**");//所有路径都拦截
//        /***************************放行路径**************************/
//        // 活动专区
//        interceptor.excludePathPatterns("/activity_counter");
//        interceptor.excludePathPatterns("/signup");
//        interceptor.excludePathPatterns("/activity");
//        // 登录登出路径
//        interceptor.excludePathPatterns("/loginPost");
//        interceptor.excludePathPatterns("/oa/oaLogin");
//        interceptor.excludePathPatterns("/logout");
//        // 放行获取菜单
//        interceptor.excludePathPatterns("/sysmenutree/getTree2");
//        // MGM
//        interceptor.excludePathPatterns("/mgmactivityinfo/querysingle");
//        // 线上抽奖
//        interceptor.excludePathPatterns(
//                "/activityverification/jumping",
//                "/wechatsign/signature",
//                "/picture/download",
//                "/count/pvuv",
//                "/online/initactivityinfo",
//                "/forward/html",
//                "/enter/saveuserinfo",
//                "/query/applyicon",
//                "/online/onlineenter");
//        /***************************放行路径**************************/
//    }
//}
