package com.jt.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.pojo.ItemCat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author van
 * @date 2019/10/25--12:09
 */
public interface ItemCatMapper extends SysMapper<ItemCat> {

    List<ItemCat> findAllItemCat();

    List<ItemCat> findItemCatByParentId(Long parentId);
}
