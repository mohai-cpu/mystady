package hello.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.taikang.hello.utils.SentinelUrlBlockHandler;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2013:46
 */
@Configuration
public class SentinelConfig {
    @PostConstruct
    public void init(){
        WebCallbackManager.setUrlBlockHandler(new SentinelUrlBlockHandler());
    }
}
