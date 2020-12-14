package com.taikang.oauth2.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2513:47
 */
@Data
@Entity
@Table(name = "titan_user")
public class TitanUser implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //用户id
    @Column(name = "uid")
    private String uid;
    //账户名称
    @Column(name = "username")
    private String username;
    //账户密码
    @Column(name = "password")
    private String password;
    //用户名称
    @Column(name = "fullname")
    private String fullname;
    //手机号
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "createdate")
    private Timestamp createDate = new Timestamp(System.currentTimeMillis());
    @Column(name="modifydate")
    private Timestamp modifyDate = new Timestamp(System.currentTimeMillis());

}
