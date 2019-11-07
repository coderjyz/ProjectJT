package com.jt.manage.service;

import com.jt.common.vo.TreeNode;
import com.jt.pojo.ItemCat;

import java.util.List;

/**
 * @author van
 * @date 2019/10/29--17:10
 */
public interface ManagerItemCatService {
    List<TreeNode> getItemCatList(Long id);
}
