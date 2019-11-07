package com.jt.service;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.pojo.ItemParam;

/**
 * @author van
 * @date 2019/10/29--16:51
 */
public interface ItemParamService {
    int insertItemParam(ItemParam itemParam);
    public EasyUIResult loadItemParamByPage(Integer page,Integer rows);
    public SysResult saveItemParamService(ItemParam itemParam);
}
