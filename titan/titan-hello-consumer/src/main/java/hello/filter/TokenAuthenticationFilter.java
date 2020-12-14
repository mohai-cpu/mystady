package hello.filter;//package com.taikang.hello.filter;
//
//import cn.hutool.core.codec.Base64;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.taikang.com.taikang.common.dto.TitanUserDTO;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class TokenAuthenticationFilter extends OncePerRequestFilter {
//    private Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        logger.info("-----------------------进入资源过滤器-------------------");
//        String token = request.getHeader("user-token");
//        logger.info("doFilterInternal;令牌token:{}", token);
//        if (StringUtils.isNotBlank(token)) {
//            String json = Base64.decodeStr(token);
//            // String json = EncryptUtil.decodeUTF8StringBase64(token);
//            JSONObject jsonObject = JSON.parseObject(json);
//            //获取用户身份信息、权限信息
//            String principal = jsonObject.getString("principal");
//            TitanUserDTO userDTO = JSON.parseObject(principal, TitanUserDTO.class);
//            JSONArray tempJsonArray = jsonObject.getJSONArray("authorities");
//            String[] authorities = tempJsonArray.toArray(new String[0]);
//            logger.info("doFilterInternal;用户信息principal:{},userDTO:{}", principal, JSONObject.toJSONString(userDTO));
//            logger.info("doFilterInternal;权限信息tempJsonArray:{}", tempJsonArray.toJSONString());
//            //身份信息、权限信息填充到用户身份token对象中
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO, null,
//                    AuthorityUtils.createAuthorityList(authorities));
//            //创建details
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            //将authenticationToken填充到安全上下文
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//        filterChain.doFilter(request, response);
//    }
//}
