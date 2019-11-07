package com.jt.manage.service;

import com.jt.common.vo.TreeNode;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author van
 * @date 2019/10/29--17:11
 */
@Service
public class ManagerItemCatServiceImpl implements ManagerItemCatService {

    @Autowired
    private ItemCatService itemCatServiceProxy;

    @Override
    public List<TreeNode> getItemCatList(Long id) {
        //调用远程服务
        List<ItemCat> itemCats = itemCatServiceProxy.findItemCatListByParentId(id);
        //集合转换
        List<TreeNode> treeNodes = new ArrayList<>();
        for (ItemCat cat :itemCats){
            TreeNode treeNode = new TreeNode();
            treeNode.setId(cat.getId());
            treeNode.setText(cat.getName());
            treeNode.setState(cat.getIsParent()?"closed":"open");
            treeNodes.add(treeNode);
        }
        return treeNodes;
    }
}
