package com.jt.manage.controller;

import com.jt.common.vo.TreeNode;
import com.jt.manage.service.ManagerItemCatService;
import com.jt.manage.service.ManagerItemCatServiceImpl;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.midi.Soundbank;
import java.util.List;

/**
 * @author van
 * @date 2019/10/25--11:52
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    ManagerItemCatService managerItemCatService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemCatController.class);

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<TreeNode> itemCatList(@RequestParam(defaultValue = "0",required = false)Long id){
        List<TreeNode> list = managerItemCatService.getItemCatList(id);
        for(TreeNode treeNode:list){
            System.out.println(treeNode.toString());
        }
        return list;
    }
}
