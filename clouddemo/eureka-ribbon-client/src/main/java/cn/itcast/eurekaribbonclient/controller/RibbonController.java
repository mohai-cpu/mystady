package cn.itcast.eurekaribbonclient.controller;

import cn.itcast.eurekaribbonclient.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @Title: RibbonController
 * @ProjectName clouddemo
 * @Description: TODO
 * @date 2020/10/9
 */
@RestController
@RequestMapping("/ribbon")
public class RibbonController {
    @Autowired
    private RibbonService ribbonService;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @RequestMapping("/hi")
    public String hi(String name){
        String hi = ribbonService.hi(name);
        System.out.println(hi);
        return hi;
    }
    @RequestMapping("/testRibbon")
    public String testRibbon(){
        ServiceInstance instance = loadBalancerClient.choose("eureka-client2");
        if(instance == null){
            return "没有信息";
        }
        return instance.getHost()+":::"+instance.getPort()+"::::"+instance.getServiceId();
    }
}
