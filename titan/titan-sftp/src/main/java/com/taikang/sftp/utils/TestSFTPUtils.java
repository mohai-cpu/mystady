package com.taikang.sftp.utils;

import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.SftpException;
import com.taikang.sftp.config.SFTP;
import com.taikang.sftp.config.SftpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *@Description SFTP工具类测试类
 *@param
 *@return
 *@Author zhangjian
 *@Date 2020/12/8 18:49
 */
public class TestSFTPUtils {

    private static final Logger logger = LoggerFactory.getLogger(TestSFTPUtils.class);
    //10.135.52.137
   // /home/tomcat/blastoise/test
    public static void main(String[] args) {
            SFTP ftp = new SFTP(3, 6000);
            SftpConfig sftpConfig = new SftpConfig("10.135.52.137", 22, "itw_zhangjian04", "Pwd_1234", 1000, "/10.130.209.51 sftp (10.130.209.51)/nginx");
            try {
                List<String> filelist = ftp.listFiles("/10.130.209.51 sftp (10.130.209.51)/nginx/home/nginx", sftpConfig);
                logger.info("文件上传下载详情filelist:{}"  , JSONObject.toJSONString(filelist));
            } catch (SftpException e) {
                logger.error("文件上传下载异常e:{}" ,e);
            }
        }
}
