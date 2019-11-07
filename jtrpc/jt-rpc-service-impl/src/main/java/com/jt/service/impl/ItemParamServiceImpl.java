package com.jt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.mapper.ItemParamMapper;
import com.jt.pojo.ItemParam;
import com.jt.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author van
 * @date 2019/10/29--16:54
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    ItemParamMapper itemParamMapper;


    @Override
    public int insertItemParam(ItemParam itemParam) {
        return 0;
    }

    @Override
    public EasyUIResult loadItemParamByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<ItemParam> items = itemParamMapper.findAll();
        PageInfo<ItemParam> itemPageInfo = new PageInfo<>(items);
        return new EasyUIResult(itemPageInfo.getTotal(),itemPageInfo.getList());
    }

    @Override
    public SysResult saveItemParamService(ItemParam itemParam) {
        itemParamMapper.insert(itemParam);
        return null;
    }
}
