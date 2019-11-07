package com.jt.manage.controller;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.pojo.Item;
import com.jt.service.ItemParamService;
import com.jt.service.ItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @author van
 * @date 2019/10/22--15:11
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    //实例化日志对象
    private static final Logger logger = Logger.getLogger(ItemController.class);

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Item> findAllItem(){
        return itemService.findAllItems();
    }

    @RequestMapping("/findPageObject")
    @ResponseBody //把查找结果转换成json串
    public EasyUIResult findPageObject(int page, int rows){
        System.out.println("------------"+page+","+rows);
        return itemService.findItemsByPage(page,rows);
    }

    @RequestMapping("/query/item/desc")
    @ResponseBody
    public SysResult findItemDescById(Long itemId){
        System.out.println("----------------------------");
        SysResult result = SysResult.build(200,null,itemService.findItemDescById(itemId));
        return result;
    }

    //记得使用produces属性，来保证响应的中文不会乱码
    @RequestMapping(value = "/queryItemCatNameById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String queryItemCatNameById(Long catId){
        System.out.println("----catId:"+catId+"----");
        return itemService.findItemCatById(catId);
    }

    @RequestMapping("/saveItem")
    @ResponseBody
    public SysResult saveItem(Item item,String desc,String itemParams){
        try {
            itemService.saveItem(item,desc,itemParams);
            logger.info("商品插入成功："+item.getId());
           return SysResult.build(200,"新增商品成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            return SysResult.build(201,"商品插入失败。");
        }
    }

    @RequestMapping("/updateItem")
    @ResponseBody
    public SysResult updateItem(Item item,String desc){
        try {
            itemService.updateItem(item,desc);
            logger.info("商品更新成功："+item.getId());
            return SysResult.build(200,"商品更新成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            return SysResult.build(201,"商品更新失败。");
        }
    }

    @RequestMapping("/deleteItems")
    @ResponseBody
    public SysResult deleteItem(Long[] ids){
        try {
            System.out.println("ids:"+ids);
            itemService.deleteItem(ids);
            logger.info("商品删除成功："+ Arrays.toString(ids));
            return SysResult.build(200,"商品删除成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            return SysResult.build(201,"商品删除失败。");
        }
    }

    //商品的上下架
    @RequestMapping("/instock")
    @ResponseBody
    public SysResult instockItem(Long[] ids){
        try{
            itemService.updateStatus(ids,2);
            logger.info("商品下架成功："+ Arrays.toString(ids));
            return SysResult.build(200,"商品下架成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            return SysResult.build(201,"商品下架失败。");
        }
    }

    @RequestMapping("/reshelf")
    @ResponseBody
    public SysResult reshelfItem(Long[] ids){
        try{
            itemService.updateStatus(ids,1);
            logger.info("商品下架成功："+ Arrays.toString(ids));
            return SysResult.build(200,"商品上架成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            return SysResult.build(201,"商品上架失败。");
        }
    }






}
