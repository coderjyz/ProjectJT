package com.jt.common.vo;

import java.net.Inet4Address;

/**
 * 图片上传响应结果
 * @author van
 * @date 2019/10/31--18:35
 */
public class PictureResult {
    private Integer error;
    private String url;
    private String message;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
