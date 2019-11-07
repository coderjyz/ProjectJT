package com.jt.manage.service;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.pojo.ItemParam;

/**
 * @author van
 * @date 2019/11/1--10:54
 */
public interface ManagerItemParamService {
    public EasyUIResult loadItemParamByPage(Integer page, Integer rows);
    public SysResult saveItemParamService(Long cid,String paramData);
}
