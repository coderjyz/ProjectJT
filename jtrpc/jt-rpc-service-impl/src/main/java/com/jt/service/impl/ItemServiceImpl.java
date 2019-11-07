package com.jt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.mapper.ItemParamItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.ItemParam;
import com.jt.pojo.ItemParamItem;
import com.jt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author van
 * @date 2019/10/22--11:37
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Autowired
    private ItemParamItemMapper itemParamItemMapper;

    public List<Item> findAllItems() {
        List<Item> items = itemMapper.findAllItems();
        return items;
    }

    public EasyUIResult findItemsByPage(int page, int pageSize) {
        //使用了分页插件PageHelper，无需自己计算分页数据
        //大体原理:在PageHelper类加载时，会生成一个ThreadLocal类的常量LOCAL_PAGE用来记录分页信息
        //ThreadLocal可以实现数据隔离
        PageHelper.startPage(page,pageSize);
        List<Item> items = itemMapper.findAllItems();
        //相当于之前写的PageObject，但是功能更加强大
        PageInfo<Item> itemPageInfo = new PageInfo<Item>(items);
        return new EasyUIResult(itemPageInfo.getTotal(),itemPageInfo.getList());
    }

    public String findItemCatById(Long catId) {
        return itemMapper.queryItemCatById(catId);
    }

    public int saveItem(Item item, String description, String itemParamItem) {
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());//因为是新插入，所以创建时间和更改时间应该时一样的
        //注意，这一步操作必须在创建商品描述对象之前，因为要使用商品自动生成的主键
        int rows = itemMapper.insert(item);

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(description);
        itemDesc.setItemId(item.getId());
        itemDesc.setCreated(item.getCreated());
        itemDesc.setUpdated(item.getCreated());
        itemDescMapper.insert(itemDesc);

        ItemParamItem paramItem = new ItemParamItem();
        paramItem.setItemId(item.getId());
        paramItem.setParamData(itemParamItem);
        itemParamItemMapper.insert(paramItem);

        if(rows==0){
            throw new RuntimeException("插入商品失败");
        }

        return rows;
    }

    public int deleteItem(Long[] itemIds) {
        itemMapper.deleteByIDS(itemIds);
        return 0;
    }

    public int updateItem(Item item, String desc) {
        item.setUpdated(new Date());
        //使用这个方法会在更新商品信息的时候选择性更新，如果有某项内容为null则不会更新
        itemMapper.updateByPrimaryKeySelective(item);

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        itemDesc.setUpdated(item.getUpdated());
        itemDescMapper.updateByPrimaryKeySelective(itemDesc);
        return 0;
    }

    public int updateStatus(Long[] itemIds, int status) {
        itemMapper.changeStatus(itemIds,status);
        return 0;
    }

    public String findItemDescById(Long itemId) {
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(itemId);
        List<ItemDesc> result = itemDescMapper.select(itemDesc);
        return result.get(0).getItemDesc();
    }
}
