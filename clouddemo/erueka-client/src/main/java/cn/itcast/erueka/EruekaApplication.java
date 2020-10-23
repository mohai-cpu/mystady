package cn.itcast.erueka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EruekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EruekaApplication.class, args);
    }

}
