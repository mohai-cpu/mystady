package hello.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.taikang.hello.utils.ExceptionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description  资源限流自定义
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2014:58
 */
@RestController
public class ParamController {
    @GetMapping(value = "/resource")
    @SentinelResource(value = "resource",blockHandler = "exHandler",blockHandlerClass = {ExceptionUtil.class})
    public String resource(){
        return "sentinel resource";
    }
    @RequestMapping(value = "/param",method = RequestMethod.GET)
    @SentinelResource(value = "param",blockHandler = "exHandler")
    public String param(String type){
        return "success";
    }
    public String exHandler(String type, BlockException ex){
        return "热点限流成功";
    }
}
