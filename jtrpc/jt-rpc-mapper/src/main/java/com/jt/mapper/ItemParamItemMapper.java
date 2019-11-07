package com.jt.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.pojo.ItemParamItem;

/**
 * @author van
 * @date 2019/11/1--20:12
 */
public interface ItemParamItemMapper extends SysMapper<ItemParamItem> {
    ItemParamItem findById(Long itemId);
}
