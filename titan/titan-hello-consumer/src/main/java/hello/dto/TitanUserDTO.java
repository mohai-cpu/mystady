package hello.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2513:47
 */
@Data
public class TitanUserDTO implements Serializable {

    private long id;
    //用户id
    private String uid;
    //账户名称
    private String username;
    //账户密码
    private String password;

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
}
