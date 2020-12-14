package hello.utils;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;


/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2014:45
 */
public class ExceptionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);
    public static String exHandler(BlockException ex){
        return "熔断降级";
    }
    //限流熔断业务逻辑
    public static SentinelClientHttpResponse handleBlock(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        logger.info("限流生效了");
        return new SentinelClientHttpResponse("限流熔断");
    }

    //异常降级熔断业务逻辑
    public static SentinelClientHttpResponse handleFallback(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        logger.info("熔断降级生效了");
        return new SentinelClientHttpResponse("异常降级业务熔断");
    }
}
