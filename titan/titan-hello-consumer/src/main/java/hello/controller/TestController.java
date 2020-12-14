package hello.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import common.ReturnResult;
import hello.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Value("${service-url.dx}")
    private String dx;

    @Value("${service-url.transfer}")
    private String transfer;

    @Value("${service-url.oredpack}")
    private String oredpack;

    @Value("${service-url.eredpack}")
    private String eredpack;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/test/{flag}")
    public ReturnResult demo(@PathVariable String flag) {
        if ("dx".equals(flag)) {
            ReturnResult result = restTemplate.getForObject(dx + "/test/" + flag, ReturnResult.class);
            logger.info("demo;result:{}", JSONObject.toJSONString(result));
            return result;
        }
        if ("transfer".equals(flag)) {
            return restTemplate.getForObject(transfer + "/test/" + flag, ReturnResult.class);
        }
        if ("oredpack".equals(flag)) {
            return restTemplate.getForObject(oredpack + "/test/" + flag, ReturnResult.class);
        }
        if ("eredpack".equals(flag)) {
            return restTemplate.getForObject(eredpack + "/test/" + flag, ReturnResult.class);
        }
        ReturnResult result = new ReturnResult();
        result.setCode(2001);
        return result;
    }
    @GetMapping(value = "/test/health")
    public String healthCheck(){
        return "健康检查成功";
    }
    int temp=1;
    @GetMapping(value = "/hello/jump")
    @SentinelResource(value = "jump",fallback = "exHandler",fallbackClass = ExceptionUtil.class)
    public String jump(){
        //模拟线程睡眠
        /*try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
      /*  temp++;
        if(temp%3==0){
            throw new RuntimeException("系统异常");
        }*/
        return "跳转成功";
    }
    @RequestMapping(value = "/test/re",method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('p3')")
    //@PreAuthorize("hasAnyAuthority('p3')")
    public String testResource(){
        logger.info("******************认证访问资源成功***************");
        return "访问的资源1";
    }
    @RequestMapping(value = "/ceshi",method = RequestMethod.POST)
    public String ceshi(HttpServletRequest request, HttpServletResponse response){
        String queryString = request.getQueryString();
       // JSONObject jsonObject1 = JSONObject.parseObject(queryString);
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String[]> parameterMap2 = request.getParameterMap();
        logger.info("ceshi;queryString:{}",queryString);
        logger.info("ceshi;parameterMap:{}",JSONObject.toJSONString(parameterMap));
        logger.info("ceshi;parameterMap2:{}",JSONObject.toJSONString(parameterMap2));
        String[] strs = queryString.split("&");
        StringBuilder sb2 = new StringBuilder();
        /*String s = JSONObject.toJSONString(strs);
        String replace = s.replace("=", ":");
       // replace.split()
        System.out.println("*****="+replace);*/
       // JSONObject.parseObject(replace);
        /*for (int i=0;i<strs.length;i++) {
            String[] split = strs[0].split("=");
            sb2.append(split[0]+";"+)
        }*/
       // logger.info("ceshi;queryString:{}",JSONObject.toJSONString(queryString));
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
            System.out.println(sb.toString());
            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            System.out.println("id="+jsonObject.get("id"));
            logger.info("ceshi;测试parameterMap:{}",JSONObject.toJSONString(sb));
        } catch (IOException e) {

            e.printStackTrace();
            return "222222222";
        }
        //Map<String, String[]> parameterMap = request.getParameterMap();
        return "ceshi";
    }

    @RequestMapping(value = "/ceshi",method = RequestMethod.GET)
    public ReturnResult getIp(){
        ReturnResult result = new ReturnResult();
        result.setCode(2000);
        return result;
    }
}
