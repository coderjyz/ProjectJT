package com.jt.manage.controller;

import com.jt.common.vo.PictureResult;
import com.jt.manage.service.ManagerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

/**
 * @author van
 * @date 2019/10/31--21:10
 */
@Controller
public class ItemImageController {
    @Autowired
    private ManagerItemService managerItemService;

    @RequestMapping(value = "pic/upload",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PictureResult picUpload(MultipartFile uploadFile){
        if(uploadFile==null){
            throw new RuntimeException("文件为空");
        }
        return managerItemService.uploadItemPic(uploadFile);
    }
}
