package com.taikang.oauth2.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.taikang.oauth2.entity.TitanUser;
import com.taikang.oauth2.repository.TitanUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2515:18
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private TitanUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername;账号名称username:{}",username);
        TitanUser TUser = userRepository.findByUserName(username);
        if(TUser ==null){
            return null;
        }
        logger.info("loadUserByUsername;用户详情TUser:{}",JSONObject.toJSONString(TUser.toString()));
        List<String> permissions = userRepository.findByUid(TUser.getUid());
        logger.info("loadUserByUsername;用户权限permissions:{}",JSONObject.toJSONString(permissions.toString()));
        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        UserDetails userDetails = User.withUsername(JSONObject.toJSONString(TUser)).password(TUser.getPassword()).authorities(permissionArray).build();
        logger.info("loadUserByUsername;用户详细信息userDetails:{}",JSONObject.toJSONString(userDetails));
        return userDetails;
    }
}
