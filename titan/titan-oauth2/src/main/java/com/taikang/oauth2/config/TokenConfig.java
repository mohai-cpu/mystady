package com.taikang.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Description  令牌配置
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2416:32
 */
@Configuration
public class TokenConfig {
    private String SINGING_KEY = "uaa123";//签名密钥
    /**
     *@Description 令牌存储策略 内存
     */
//    @Bean
//    public TokenStore tokenStore(){
//        //内存方式生成普通令牌
//        return new InMemoryTokenStore();
//    }
    //生成jwt令牌
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SINGING_KEY);
        return converter;
    }

}
