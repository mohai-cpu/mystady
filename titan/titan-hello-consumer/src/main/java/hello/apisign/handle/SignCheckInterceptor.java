package hello.apisign.handle;

import com.alibaba.fastjson.JSONObject;
import com.taikang.common.Result;
import com.taikang.hello.apisign.utils.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/12/415:30
 */
@Component
public class SignCheckInterceptor implements HandlerInterceptor {
    private static final String secretKey = "30c722c6acc64306a88dd93a814c9f0a";
    private static final Logger logger = LoggerFactory.getLogger(SignCheckInterceptor.class);
    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     *@Description   请求前执行  进行签名验证
     *@param httpServletRequest, httpServletResponse, obj
     *@return boolean
     *@Author zhangjian
     *@Date 2020/12/4 15:32
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj) throws Exception {
        Result result = new Result();
        boolean handleResult = false;
        //get请求
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        if(parameterMap==null){
            result.setCode(2001);
            result.setMsg("请求参数为空");
            returnJson(httpServletResponse,result);
            return handleResult;
        }
        logger.info("preHandle;请求参数parameterMap:{}",JSONObject.toJSONString(parameterMap));
        //将map的数组的值String[]转换成string
        Map<String, String> stringMap = SignUtil.toVerifyMap(parameterMap, false);
        // 供应商的id，验证用户的真实性
        String accessKey = stringMap.get("accessKey");
        // 请求发起的时间
        String timestamp = stringMap.get("timestamp");
        // 随机数
        String nonce = stringMap.get("nonce");
        // 签名算法生成的签名
        String sign = stringMap.get("sign");
        logger.info("preHandle;请求参数accessKey:{};timestamp:{};nonce:{};sign:{}",accessKey,timestamp,nonce,sign);
        if(StringUtils.isAnyBlank(accessKey,timestamp,nonce,sign)){
            result.setCode(2001);
            result.setMsg("请求参数为空");
            returnJson(httpServletResponse,result);
            return handleResult;
        }
        //添加密钥（不通过参数传递的）
        stringMap.put("secretKey",secretKey);
        // 限制为（含）60秒以内发送的请求
        long time = 60;//单位秒
        long now = System.currentTimeMillis();//单位毫秒
        Long timeDiff = (now - Long.valueOf(timestamp))/1000;
        if (timeDiff > time) {
            result.setCode(2002);
            result.setMsg("请求发起时间超过服务器限制时间");
            returnJson(httpServletResponse,result);
            return handleResult;
        }
        if(redisTemplate.opsForHash().hasKey("third_key",accessKey+nonce)){
            result.setCode(2003);
            result.setMsg("请不要发送重复的请求");
            returnJson(httpServletResponse,result);
            return handleResult;
        }else {
            //记录这次请求设置过期时间
            redisTemplate.opsForHash().putIfAbsent("third_key",accessKey+nonce,nonce);
            redisTemplate.expire("third_key",60, TimeUnit.SECONDS);
        }
        //签名处理  获取生成后的签名map
        Map<String, String> signMap = SignUtil.sign(stringMap);
        String mysign = signMap.get("sign");
        logger.info("preHandle;新生成的mysign:{}",mysign);
        // 验证签名
        if (!mysign.equals(sign)) {
            result.setCode(2004);
            result.setMsg("签名信息错误");
            returnJson(httpServletResponse,result);
            return handleResult;
        }
        handleResult = true;
        return handleResult;
    }
    //请求结束执行
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    //试图渲染完成后执行
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
    private void returnJson(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(JSONObject.toJSONString(result));
        } catch (IOException e) {
            logger.info("SignCheckInterceptor returnJson Exception :{}", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
