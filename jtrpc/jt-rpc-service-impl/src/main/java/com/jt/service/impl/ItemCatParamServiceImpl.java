package com.jt.service.impl;

import com.jt.mapper.ItemParamMapper;
import com.jt.pojo.ItemParam;
import com.jt.service.ItemCatParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author van
 * @date 2019/10/25--17:47
 */
@Service
public class ItemCatParamServiceImpl implements ItemCatParamService {

    @Autowired
    ItemParamMapper itemParamMapper;

    @Override
    public String findParamByCatId(Long catId) {
        ItemParam itemCatParam = new ItemParam();
        itemCatParam.setItemCatId(catId);
        List<ItemParam> result = itemParamMapper.select(itemCatParam);
        if(result.size() == 0){
            return null;
        }
        return result.get(0).getParamData();
    }
}
