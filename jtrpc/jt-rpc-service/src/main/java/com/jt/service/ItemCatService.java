package com.jt.service;

import com.jt.common.vo.MenuTree;
import com.jt.pojo.ItemCat;

import javax.swing.tree.TreeNode;
import java.util.List;

/**
 * @author van
 * @date 2019/10/25--11:55
 */
public interface ItemCatService  {
    List<MenuTree> findAllItemCat();
    List<ItemCat> findItemCatListByParentId(Long id);
}
