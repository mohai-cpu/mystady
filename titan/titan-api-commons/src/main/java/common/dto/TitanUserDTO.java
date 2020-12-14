package common.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2513:47
 */

public class TitanUserDTO implements Serializable {

    private long id;
    //用户id
    private String uid;
    //账户名称
    private String username;
    //账户密码
    private String password;
    //用户名称
    private String fullname;
    //手机号
    private String mobile;
    private Timestamp createDate = new Timestamp(System.currentTimeMillis());
    private Timestamp modifyDate = new Timestamp(System.currentTimeMillis());

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }
}
