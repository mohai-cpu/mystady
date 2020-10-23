package cn.itcast.eurekaribbonclient.service.impl;

import cn.itcast.eurekaribbonclient.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 * @Title: RibbonServiceImpl
 * @ProjectName clouddemo
 * @Description: TODO
 * @date 2020/10/9
 */
@Service
public class RibbonServiceImpl implements RibbonService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String hi(String name) {
        return restTemplate.getForObject("http://eureka-client/first/hi?name="+name,String.class);
    }
}
