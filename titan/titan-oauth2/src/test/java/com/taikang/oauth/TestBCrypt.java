package com.taikang.oauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2810:54
 */
@RunWith(SpringRunner.class)
public class TestBCrypt {
    @Test
    public void testBCrypt(){
        //对密码加密
        String hashpw = BCrypt.hashpw("123456",BCrypt.gensalt());
        String hashpw2 = BCrypt.hashpw("secret",BCrypt.gensalt());
        System.out.println("123456密码："+hashpw);
        System.out.println("secret密码："+hashpw2);
        //校验密码
        boolean checkpw = BCrypt.checkpw("123456","$2a$10$4PT4jzPqyvRnrmdGy4dID.O0rPAtxHAE3qfE9E65VRsMLixCm6dv6");
        boolean checkpw2 = BCrypt.checkpw("123456","$2a$10$Mo2XuIFRiradVLw3rzHeEuYxZPM5z6Zc9UXzEm1VBLlE0CFjEH4cK");
        System.out.println(checkpw+"**************"+checkpw2);
    }
}
