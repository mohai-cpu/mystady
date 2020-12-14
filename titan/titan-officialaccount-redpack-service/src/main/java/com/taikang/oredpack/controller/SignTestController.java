package com.taikang.oredpack.controller;

import com.alibaba.fastjson.JSONObject;
import com.taikang.common.Result;
import com.taikang.oredpack.apisign.utils.SignUtil;
import com.taikang.oredpack.dto.TitanUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/12/711:12
 */
@RestController
@RequestMapping("/signapi")
public class SignTestController {
    private static final String accessKey = "txbceshi";
    private static final String secretKey = "30c722c6acc64306a88dd93a814c9f0a";
    private static final Logger logger = LoggerFactory.getLogger(SignTestController.class);
    @RequestMapping(value = "/sign1",method = RequestMethod.GET)
    public Result sign1(String uid,String username,String sign, String timestamp,String nonce,String accessKey){
        logger.info("sign1;请求参数accessKey:{};timestamp:{};nonce:{};sign:{}",accessKey,timestamp,nonce,sign);
        Result result = new Result();
        result.setCode(2000);
        result.setMsg("访问资源成功");
        TitanUserDTO userDTO = new TitanUserDTO();
        userDTO.setId(11L);
        userDTO.setUid(uid);
        userDTO.setUsername(username);
        result.setData(userDTO);
        return result;
    }
    //1607322723165
    //uuid  63f77b238e664225b9fdbd0191b4b432
    public static void main(String[] args) {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("uid","888888");
        stringMap.put("username","李四");
        stringMap.put("accessKey",accessKey);
        stringMap.put("secretKey",secretKey);
        stringMap.put("timestamp","1607322723165");
        stringMap.put("nonce","63f77b238e664225b9fdbd0191b4b432");
        logger.info("preHandle;签名的map参数stringMap:{}", JSONObject.toJSONString(stringMap));
        Map<String, String> mysign = SignUtil.sign(stringMap);
        String sign = mysign.get("sign");
        /*long time = System.currentTimeMillis();
        System.out.println(time);
        time = time/1000;
        System.out.println(time);*/
      /*  String uuid2 = UUID.randomUUID().toString().replace("-","");
        System.out.println(uuid2);*/
        System.out.println(sign);//0c5adc52899fec2d0cf923dcbbc4dbd1
    }
}
