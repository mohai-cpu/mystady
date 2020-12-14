package hello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2610:02
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;
    public static final String RESOURCE_ID="res1";
    /**
     *@Description  资源服务配置
     *@param resources
     *@return void
     *@Author zhangjian
     *@Date 2020/11/26 10:15
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)//资源id
               .tokenStore(tokenStore)
               // .tokenServices(tokenService()) //令牌验证服务
                .stateless(true);
    }

   /**
    *@Description 请求匹配以及规则配置
    *@param http
    *@return void
    *@Author zhangjian
    *@Date 2020/11/26 10:15
    */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test/**")
                .access("#oauth2.hasScope('ROLE_ADMIN')")//含有ROLE_ADMIN才能访问
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    /**
     *@Description  资源服务令牌解析服务
     *@param
     *@return org.springframework.security.oauth2.provider.token.ResourceServerTokenServices
     *@Author zhangjian
     *@Date 2020/11/26 10:11
     */
//    @Bean
//    public ResourceServerTokenServices tokenService() {
//        //使用远程服务请求授权服务器校验token必须指定校验token的url、client_id,client_secret
//        RemoteTokenServices services = new RemoteTokenServices();
//        services.setCheckTokenEndpointUrl("http://localhost:8086/oauth/check_token");//远程验证调用使用
//        services.setClientId("titan");
//        services.setClientSecret("secret");
//        return services;
//    }

}
