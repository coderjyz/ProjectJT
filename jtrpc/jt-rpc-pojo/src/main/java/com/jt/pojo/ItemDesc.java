package com.jt.pojo;

import com.jt.common.po.BasePojo;

import javax.persistence.Table;

/**
 * @author van
 * @date 2019/10/22--13:26
 */
@Table(name="tb_item_desc")
public class ItemDesc extends BasePojo {

    private long itemId;
    private String itemDesc;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
