package com.jt.manage.service;

import com.jt.common.vo.SysResult;
import com.jt.pojo.ItemParamItem;
import com.jt.service.ParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author van
 * @date 2019/11/1--21:25
 */
@Service
public class ManagerParamItemServiceImpl implements ManagerParamItemService {

    @Autowired
    ParamItemService paramItemServiceProxy;

    @Override
    public SysResult loadParamItemService(Long itemId) {
        ItemParamItem itemParamItem=
        paramItemServiceProxy.loadItemParamItemService(itemId);
        return SysResult.build(200,"find it.",itemParamItem);
    }
}
