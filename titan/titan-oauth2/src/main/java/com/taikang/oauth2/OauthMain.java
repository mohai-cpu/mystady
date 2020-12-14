package com.taikang.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2415:44
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OauthMain {
    public static void main(String[] args) {
        SpringApplication.run(OauthMain.class,args);
    }
}
