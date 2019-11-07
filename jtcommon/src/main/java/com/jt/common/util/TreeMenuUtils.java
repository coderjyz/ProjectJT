package com.jt.common.util;

import com.jt.common.vo.MenuTree;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EasyUI tree组件工具类，为了生成符合EasyUi格式的json数据
 * @author van
 * @date 2019/10/25--12:59
 */
public class TreeMenuUtils {

    public final static List<MenuTree> getFatherNodes(List<MenuTree> treeList){
        List<MenuTree> tree = new ArrayList<>();
        for(MenuTree t:treeList){
            //System.out.println("1111111111111TreeMenuUtils1111111111111111");
            if(StringUtils.isEmpty(t.getPid())) {
                t.setChildren(getChildrenNode(t.getId(), treeList));
                tree.add(t);
            }
        }
        return tree;
    }

    private final static List<MenuTree> getChildrenNode(String pId,List<MenuTree> treeList){
        List<MenuTree> tree = new ArrayList<>();
        for(MenuTree t:treeList){
            //因为传入的是treeList，所以有可能还会遍历到最上层的父节点，没有必要再向下进行
            if(StringUtils.isEmpty(t.getPid()))continue;
            if(t.getPid().equals(pId)){
                //使用递归再检查子类目中是否还有子类目，直到没有子类目为止，从而生成嵌套结构
                t.setChildren(getChildrenNode(t.getId(),treeList));
                Map<String,Object> map = new HashMap<>();
                map.put("url",t.getUrl());
                t.setAttributes(map);
                tree.add(t);
            }
        }
        return tree;
    }
}
