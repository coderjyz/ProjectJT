package com.jt.service;

import com.jt.pojo.ItemParamItem;

/**
 * @author van
 * @date 2019/11/1--21:09
 */
public interface ParamItemService {
    /**
     * 根据商品的id查询参数信息
     * @param itemId
     * @return
     */
    public ItemParamItem loadItemParamItemService(Long itemId);
}
