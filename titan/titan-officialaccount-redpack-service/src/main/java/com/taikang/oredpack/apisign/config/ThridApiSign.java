package com.taikang.oredpack.apisign.config;//package com.taikang.hello.apisign.config;
//
//import com.alibaba.fastjson.JSONObject;
//import com.taikang.common.Result;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.util.DigestUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.UnsupportedEncodingException;
//import java.util.Arrays;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Description
// * @Author:itw_zhangjian04
// * @Date： 2020/12/410:00
// */
//@Configuration
//@Aspect
//public class ThridApiSign {
//    private static final Logger logger = LoggerFactory.getLogger(ThridApiSign.class);
//    private static final String secretKey = "30c722c6acc64306a88dd93a814c9f0a";
//    @Autowired
//    private HttpServletRequest request;
//    @Autowired
//    private HttpServletResponse response;
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//    @Autowired
//    private HashOperations<String, String, String> hashOperations;
//    private final static String INPUT_CHARSET = "UTF-8";
//
//    /**
//     * 表示匹配带有自定义注解的方法
//     */
//    @Pointcut("execution(* com.taikang.hello.controller.TestController.*(..)))")
//    public void pointcut() {
//    }
//
//
//    @Around("pointcut()")
//    public Result around(ProceedingJoinPoint point) {
//        Result returnResult = new Result();
//        try {
//            // 供应商的id，验证用户的真实性
//            String accessKey = request.getHeader("accessKey");
//            // 请求发起的时间
//            String timestamp = request.getHeader("timestamp");
//            // 随机数
//            String nonce = request.getHeader("nonce");
//            // 签名算法生成的签名
//            String sign = request.getHeader("sign");
//            logger.info("around;请求头参数accessKey:{};timestamp:{};nonce:{};sign:{}",accessKey,timestamp,nonce,sign);
//            if(StringUtils.isAnyBlank(accessKey,timestamp,nonce,sign)){
//                returnResult.setCode(2001);
//                returnResult.setMsg("请求头参数不能为空");
//                return returnResult;
//            }
//            // 限制为（含）60秒以内发送的请求
//            long time = 60;
//            long now = System.currentTimeMillis() / 1000;
//            if (now - Long.valueOf(timestamp) > time) {
//                returnResult.setCode(2002);
//                returnResult.setMsg("请求发起时间超过服务器限制时间");
//                return returnResult;
//            }
//            if(hashOperations.hasKey("third_key",accessKey+nonce)){
//                returnResult.setCode(2003);
//                returnResult.setMsg("请不要发送重复的请求");
//                return returnResult;
//            }else {
//                hashOperations.putIfAbsent("third_key",accessKey+nonce,nonce);
//                redisTemplate.expire("third_key",60,TimeUnit.SECONDS);
//            }
//            JSONObject signObj = new JSONObject();
//            signObj.put("accessKey", accessKey);
//            signObj.put("timestamp", timestamp);
//            signObj.put("nonce", nonce);
//            String mySign = getSign(signObj, secretKey);
//            // 验证签名
//            if (!mySign.equals(sign)) {
//                returnResult.setCode(2003);
//                returnResult.setMsg("签名信息错误");
//                return returnResult;
//            }
//            try {
//                 point.proceed();
//            } catch (Throwable throwable) {
//                logger.info("around;签名解析异常throwable:{}",JSONObject.toJSONString(throwable));
//                returnResult.setCode(2004);
//                returnResult.setMsg("签名验证异常");
//                return returnResult;
//            }
//        } catch (Exception e) {
//            logger.info("around;签名解析异常throwable:{}",e);
//            returnResult.setCode(2004);
//            returnResult.setMsg("签名验证异常");
//            return returnResult;
//        }
//        returnResult.setCode(2000);
//        returnResult.setMsg("签名验证通过");
//        return returnResult;
//    }
//
//
//
//    /**
//     * 获取签名信息
//     * @param data
//     * @param secret
//     * @return
//     */
//    private static String getSign(JSONObject data, String secret) {
//        // 由于map是无序的，这里主要是对key进行排序（字典序）
//        Set<String> keySet = data.keySet();
//        String[] keyArr = keySet.toArray(new String[keySet.size()]);
//        Arrays.sort(keyArr);
//        StringBuilder sbd = new StringBuilder();
//        for (String k : keyArr) {
//            if (StringUtils.isNotBlank(data.getString(k))) {
//                sbd.append(k + "=" + data.getString(k) + "&");
//            }
//        }
//        // secret最后拼接
//        sbd.append("secret=").append(secret);
//
//        String mysign = DigestUtils.md5DigestAsHex(getContentBytes( sbd.toString().toUpperCase(), INPUT_CHARSET));
//        return mysign;
//    }
//    /**
//     * 编码转换
//     *
//     * @param content
//     * @param charset
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    private static byte[] getContentBytes(String content, String charset) {
//        if (charset == null || "".equals(charset)) {
//            return content.getBytes();
//        }
//        try {
//            return content.getBytes(charset);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
//        }
//    }
//}
