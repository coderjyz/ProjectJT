package com.jt.manage.service;

import com.jt.common.util.FtpUtils;
import com.jt.common.util.IDUtils;
import com.jt.common.vo.PictureResult;
import com.jt.common.vo.SysResult;
import com.jt.pojo.Item;
import com.jt.pojo.ItemParamItem;
import com.jt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author van
 * @date 2019/10/29--10:45
 */
@Service
public class ManagerItemServiceImpl implements ManagerItemService {

    @Autowired
    private ItemService itemServiceProxy;

    @Value("${FTP_HOST}")
    private String FTP_HOST;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_PATH}")
    private String FTP_PATH;
    @Value("${IMAGE_HTTP_PATH}")
    private String IMAGE_HTTP_PATH;


    @Override
    public List<Item> findAllItem(Integer page, Integer rows) {
        return null;
    }

    @Override
    public PictureResult uploadItemPic(MultipartFile file) {
        boolean flag = false;
        String fileName = null;
        try{
            fileName = IDUtils.genImageName();
            String oriName = file.getOriginalFilename();
            String ext = oriName.substring(oriName.lastIndexOf("."));
            fileName = fileName+ext;
            InputStream local = file.getInputStream();
            flag = FtpUtils.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_PATH, fileName, local);
        }catch (Exception e){
            e.printStackTrace();
            flag = false;
        }
        PictureResult result = null;
        if(flag){
            result = new PictureResult();
            result.setError(0);
            result.setUrl(IMAGE_HTTP_PATH+"/"+fileName);
            result.setMessage("ok");
        }else {
            result = new PictureResult();
            result.setError(1);
            result.setUrl("url");
            result.setMessage("error");
        }
        return result;
    }



}
