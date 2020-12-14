package com.taikang.oredpack.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2513:47
 */
@Data
public class TitanUserDTO implements Serializable{

    private long id;
    //用户id
    private String uid;
    //账户名称
    private String username;
    //账户密码
    private String password;
}
