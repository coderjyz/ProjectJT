package com.jt.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.pojo.ItemParam;

import java.util.List;

/**
 * @author van
 * @date 2019/10/25--17:44
 */
public interface ItemParamMapper extends SysMapper<ItemParam> {
    List<ItemParam> findAll();

}
