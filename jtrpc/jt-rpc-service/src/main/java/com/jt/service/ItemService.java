package com.jt.service;

import com.jt.common.vo.EasyUIResult;
import com.jt.pojo.Item;
import com.jt.pojo.ItemParam;
import com.jt.pojo.ItemParamItem;

import java.util.List;

/**
 * @author van
 * @date 2019/10/22--10:42
 */
public interface ItemService {
    List<Item> findAllItems();
    EasyUIResult findItemsByPage(int page,int pageSize);
    String findItemCatById(Long catId);
    int saveItem(Item item, String description, String itemParamItem);
    int deleteItem(Long[] itemIds);
    int updateItem(Item item,String desc);
    int updateStatus(Long[] itemIds,int status);
    String findItemDescById(Long itemId);
}
