package com.taikang.sftp;

import com.taikang.sftp.config.SFTP;
import com.taikang.sftp.config.SftpConfig;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.jcraft.jsch.*;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/12/914:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);
    @Autowired
    @Qualifier("coreSftpChannel")
    private ChannelSftp channelsftp;
    @Autowired
    private SFTP sftp;
    @org.junit.Test
    public void testUpload(){
        sftp.setChannelSftp(channelsftp);
        String directory = "/10.130.209.51 sftp (10.130.209.51)/nginx/home/nginx/blastoise/test";
        String uploadFile = "D:/woshishi/ceshi02.txt";
        SftpConfig sftpConfig = new SftpConfig("10.135.52.137", 22, "itw_zhangjian04", "Pwd_1234", 1000, "/10.130.209.51 sftp (10.130.209.51)/nginx/home/nginx/blastoise/test");
        sftp.upload(directory,uploadFile,sftpConfig);
    }
    @org.junit.Test
    public void testDownload(){
        sftp.setChannelSftp(channelsftp);
        String directory = "/10.130.209.51 sftp (10.130.209.51)/nginx/home/nginx/blastoise/test";
        String downloadFile = "ceshi02.txt";
        String saveFile = "D:/woshishi/ceshi";
        SftpConfig sftpConfig = new SftpConfig("10.135.52.137", 22, "itw_zhangjian04", "Pwd_1234", 1000, "");
        sftp.download(directory,downloadFile,saveFile,sftpConfig);
    }
    @org.junit.Test
    public void test(){
        System.out.println("8888888888888");
    }
}
