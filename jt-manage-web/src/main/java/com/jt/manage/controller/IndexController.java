package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author van
 * @date 2019/10/22--15:12
 */
@Controller
public class IndexController {

    //用Restful的风格实现页面跳转
    @RequestMapping("/page/{moduleName}")
    public String index(@PathVariable String moduleName){
        return moduleName;
    }


}
