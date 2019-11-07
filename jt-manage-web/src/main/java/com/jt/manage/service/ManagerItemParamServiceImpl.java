package com.jt.manage.service;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.pojo.ItemParam;
import com.jt.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author van
 * @date 2019/11/1--10:56
 */
@Service
public class ManagerItemParamServiceImpl implements ManagerItemParamService {

    @Autowired
    ItemParamService itemParamServiceProxy;
    @Override
    public EasyUIResult loadItemParamByPage(Integer page, Integer rows) {
        return itemParamServiceProxy.loadItemParamByPage(page,rows);
    }

    @Override
    public SysResult saveItemParamService(Long cid,String paramData) {
        ItemParam param = new ItemParam();
        param.setParamData(paramData);
        param.setItemCatId(cid);
        Date date = new Date();
        param.setCreated(date);
        param.setUpdated(date);

        return itemParamServiceProxy.saveItemParamService(param);
    }
}
