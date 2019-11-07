package com.jt.manage.controller;

import com.jt.common.vo.SysResult;
import com.jt.manage.service.ManagerParamItemService;
import com.jt.pojo.ItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author van
 * @date 2019/11/1--21:31
 */
@Controller
public class ParamItemController {

    @Autowired
    ManagerParamItemService managerParamItemService;

    @RequestMapping(value = "/param/item/query/{itemId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SysResult loadParamItem(@PathVariable Long itemId){
        return managerParamItemService.loadParamItemService(itemId);
    }
}
