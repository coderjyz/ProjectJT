package com.jt.common.vo;

import java.io.Serializable;

/**
 * @author van
 * @date 2019/10/29--17:07
 */
public class TreeNode implements Serializable {
    private static final long serialVersionUID = 908127392522853851L;

    private Long id;
    private String text;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", status='" + state + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
