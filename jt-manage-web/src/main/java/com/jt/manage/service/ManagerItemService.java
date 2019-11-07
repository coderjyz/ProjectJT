package com.jt.manage.service;

import com.jt.common.mapper.SysMapper;
import com.jt.common.vo.PictureResult;
import com.jt.common.vo.SysResult;
import com.jt.pojo.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author van
 * @date 2019/10/29--10:28
 */
public interface ManagerItemService {
    public List<Item> findAllItem(Integer page, Integer rows);

    //处理上传图片
    public PictureResult uploadItemPic(MultipartFile file);

    //保存商品完整版
    //public SysResult saveItem(Item item,String desc,String paramData);
}
