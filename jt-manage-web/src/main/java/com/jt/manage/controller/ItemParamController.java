package com.jt.manage.controller;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.service.ManagerItemParamService;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author van
 * @date 2019/11/1--10:58
 */
@Controller
public class ItemParamController {
    @Autowired
    ManagerItemParamService managerItemParamService;

    @ResponseBody
    @RequestMapping(value="/item/param/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public EasyUIResult itemParamList(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue = "30")Integer rows){
        return managerItemParamService.loadItemParamByPage(page,rows);
    }

    @RequestMapping(value = "/item/param/save/{cid}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SysResult saveItemParam(@PathVariable Long cid,String paramData){
        managerItemParamService.saveItemParamService(cid,paramData);
        return SysResult.build(200,"商品更新成功！");
    }
}
