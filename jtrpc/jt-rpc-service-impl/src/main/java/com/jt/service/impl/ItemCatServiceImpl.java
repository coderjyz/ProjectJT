package com.jt.service.impl;


import com.jt.common.vo.MenuTree;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author van
 * @date 2019/10/25--11:55
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    ItemCatMapper itemCatMapper;

    public List<MenuTree> findAllItemCat() {
        //调用远程服务
        List<ItemCat> list = itemCatMapper.findAllItemCat();
        //集合转换
        List<MenuTree> nodes = new ArrayList<>();
        MenuTree node = null;
        for (ItemCat tic : list){
            node = new MenuTree();
            node.setId(tic.getId().toString());
            node.setText(tic.getName());
            node.setState(tic.getIsParent() ? "closed" : "open");
            nodes.add(node);
        }
        node = null;
        return nodes;
    }

    @Override
    public List<ItemCat> findItemCatListByParentId(Long id) {
        List<ItemCat> catList = itemCatMapper.findItemCatByParentId(id);
        System.out.println("-----------------");
        for(ItemCat itemCat:catList){
            System.out.println(itemCat.getName());
        }

        return catList;
    }
}
