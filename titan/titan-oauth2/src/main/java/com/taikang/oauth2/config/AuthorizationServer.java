package com.taikang.oauth2.config;

import com.alibaba.fastjson.JSONObject;
import org.hibernate.secure.internal.JaccSecurityListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configuration.ClientDetailsServiceConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Description 配置授权服务器
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2416:22
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(AuthorizationServer.class);
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private ClientDetailsServiceConfiguration clientDetailsServiceConfiguration;
    //将客户端信息存储到数据库
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }
  /**
   *@Description  用来配置客户端详情服务，客户端详情在这里初始化
   *@param clients
   *@return void
   *@Author zhangjian
   *@Date 2020/11/24 16:57
   */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.withClientDetails(clientDetailsService);
//        clients.inMemory()//暂时使用inmemory部署   内存
//            .withClient("titan")//clientId：（必须的）用来标识客户的Id。
//            .secret(new BCryptPasswordEncoder().encode("secret"))//客户端密钥 //客户端安全码(需要加密)
//            .resourceIds("res1")//资源 可以多个
//            .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")//该client允许的授权类型
//            .scopes("all")//用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围  也可以配置某一个服务
//            .autoApprove(false)// false 跳转到授权页面
//            .redirectUris("http://localhost:8848/nacos");//加上验证回调地址 http://wiki.taikang.com
//                .and()  //可以配置多个客户端
//                .withClient("titan")//clientId：（必须的）用来标识客户的Id。
//                .secret(new BCryptPasswordEncoder().encode("secret"))//客户端密钥 //客户端安全码(需要加密)
//                .resourceIds("res1")//资源 可以多个
//                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")//该client允许的授权类型
//                .scopes("all")//用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围  也可以配置某一个服务
//                .autoApprove(false)// false 跳转到授权页面
//                .redirectUris("http://wiki.taikang.com");//加上验证回调地址

    }

    //令牌管理服务
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service=new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);//客户端详情服务
        service.setSupportRefreshToken(true);//支持刷新令牌
        service.setTokenStore(tokenStore);//令牌存储策略
        //令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);
        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        //logger.info("tokenService;令牌有效期:{}", service.getAccessTokenValiditySeconds());
        return service;
    }

    //设置授权码模式的授权码如何存取，暂时采用内存方式
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() {
//        return new InMemoryAuthorizationCodeServices();
//    }
    //设置授权码模式的授权码如何存取，存储到数据库
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);//设置授权码模式的授权码如何存取
    }
    /**
     *@Description  用来配置令牌的访问端点和令牌服务
     *@param endpoints
     *@return void
     *@Author zhangjian
     *@Date 2020/11/24 16:52
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager) //密码模式需要
                .authorizationCodeServices(authorizationCodeServices) //授权码模式需要
                .tokenServices(tokenService()) //令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);//允许post提交
    }
    /**
     *@Description 用来配置令牌端点的安全约束
     *@param security
     *@return void
     *@Author zhangjian
     *@Date 2020/11/24 16:56
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")  //tokenkey这个endpoint当使用JwtToken且使用非对称加密时，资源服务用于获取公钥而开放的，这里指这个 endpoint完全公开
                .checkTokenAccess("permitAll()") //checkToken这个endpoint完全公开
                .allowFormAuthenticationForClients(); //允许表单认证 申请令牌

    }
}
