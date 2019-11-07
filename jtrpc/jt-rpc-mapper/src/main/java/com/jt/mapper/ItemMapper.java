package com.jt.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.pojo.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author van
 * @date 2019/10/22--10:24
 */
public interface ItemMapper extends SysMapper<Item> {
    //根据分页查找数据
    List<Item> findItemsByPage(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);
    //查找所有商品
    List<Item> findAllItems();
    //根据商品种类id查询种类名字
    String queryItemCatById(Long catId);
    //修改状态
    void changeStatus(@Param("ids") Long[] ids, @Param("status") Integer status);
    //查询商品总数
    int findItemCount();
}
