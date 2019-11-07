package com.jt.test;

import com.jt.service.impl.ItemServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author van
 * @date 2019/10/28--17:48
 */
public class ProviderTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext(
                        "spring/applicationContext-dubbo.xml",
                        "spring/applicationContext-mvc.xml",
                        "spring/applicationContext-mybatis.xml");
        ctx.start();
        try{
            System.in.read();
        }catch (IOException e){
            e.printStackTrace();
        }
        ctx.stop();
    }


}