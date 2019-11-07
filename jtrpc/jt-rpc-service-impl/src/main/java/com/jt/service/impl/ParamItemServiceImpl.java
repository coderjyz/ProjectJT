package com.jt.service.impl;

import com.jt.mapper.ItemParamItemMapper;
import com.jt.pojo.ItemParamItem;
import com.jt.service.ParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author van
 * @date 2019/11/1--21:11
 */
@Service
public class ParamItemServiceImpl implements ParamItemService {

    @Autowired
    ItemParamItemMapper itemParamItemMapper;

    @Override
    public ItemParamItem loadItemParamItemService(Long itemId) {
        /*ItemParamItem paramItem = new ItemParamItem();
        paramItem.setItemId(itemId);
        List<ItemParamItem> result = itemParamItemMapper.select(paramItem);
        return result.get(0);*/
        return itemParamItemMapper.findById(itemId);
    }
}
