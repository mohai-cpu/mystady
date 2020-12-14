package com.taikang.oredpack.controller;

import com.taikang.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    @Value("${officialaccount-redpack.config}")
    private String config;

    /**
     * 测试
     * @param flag
     * @return
     */
    @GetMapping(value = "/test/{flag}")
    public Result test(@PathVariable String flag) {
        Result result = new Result();
        result.setCode(2000);
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        map.put("port", port);
        map.put("config", config);
        result.setData(map);
        return result;
    }
    @GetMapping(value = "/test/healthCheck")
    public String healthCheck(){
        return "健康检查成功";
    }
}
