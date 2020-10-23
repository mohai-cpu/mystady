package cn.itcast.erueka.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @Title: FirstController
 * @ProjectName clouddemo
 * @Description: TODO
 * @date 2020/10/9
 */
@RestController
@RequestMapping("/first")
public class FirstController {
    @Value("${server.port}")
    private int port;
    @RequestMapping("/hi")
    public String hi(String name){
        return name+"**********"+port;
    }
}
