package com.jt.common.util;

import com.sun.tools.corba.se.idl.constExpr.BooleanAnd;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author van
 * @date 2019/10/31--18:14
 */
public class FtpUtils {

    //测试记得关闭防火墙
    public static void main(String[] args){
        String hostName = "192.168.141.128";
        int port = 21;
        String username = "ftpuser";
        String password = "coderjyz";
        String pathname = "/home/ftpuser/jd";
        String remote = "demo4.jpg";
        InputStream local = null;
        try {
            local = new FileInputStream("C://1.jpg");
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean flag = uploadFile(hostName,port,username,password,pathname,remote,local);
        System.out.println(flag);
    }

    public static boolean uploadFile(String hostName, int port, String username,
                                     String password, String pathname, String remote,
                                     InputStream local){
        boolean flag = false;
        FTPClient client = null;
        try{
            //创建ftpClient客户端对象
            client = new FTPClient();
            //建立和ftp服务器的连接
            client.connect(hostName,port);
            //登陆ftp服务器
            client.login(username,password);
            //设置上传文件的类型
            client.setFileType(FTP.BINARY_FILE_TYPE);
            //client.enterLocalPassiveMode();
            //切换工作目录，文件上传后保存到哪个目录
            if(!client.changeWorkingDirectory(pathname)){
                //如果没有这个目录则创建这个目录
                if(client.makeDirectory(pathname)){
                    client.changeWorkingDirectory(pathname);
                }
            }
            //上传文件，remote是上传后的名字，local是本地文件的名字
            client.storeFile(remote,local);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(null!=local){
                    local.close();
                }
                client.logout();
                client.disconnect();
                flag = true;
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return flag;
    }
}
