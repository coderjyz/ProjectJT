package com.jt.manage.controller;

import com.jt.common.vo.SysResult;
import com.jt.service.ItemCatParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author van
 * @date 2019/10/25--18:04
 */
@Controller
@RequestMapping("/item/param")
public class ItemCatParamController  {
    @Autowired
    private ItemCatParamService itemCatParamService;

    @RequestMapping(value="/query/itemcatid/{cid}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SysResult findParamByCatId(@PathVariable Long cid){
        String param = itemCatParamService.findParamByCatId(cid);
        return  SysResult.build(200,"find it.",param);
    }
}
