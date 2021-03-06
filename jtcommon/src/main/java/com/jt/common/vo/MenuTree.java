package com.jt.common.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author van
 * @date 2019/10/25--11:47
 */
public class  MenuTree implements Serializable {
    /*在tree_data变量中，是树控件需要的数据。其中用到的属性有：
    id：节点ID，对加载远程数据很重要。
    text: 显示节点文本。也就是菜单的标题
    state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
    attributes: 被添加到节点的自定义属性。
    children:  一个节点数组声明了若干节点。
    url: 检索远程数据的URL地址。*/
    private String id;
    private String pid;
    private String text;
    private String state;
    private String url;
    private List<MenuTree> children;
    private Map<String,Object> attributes = new HashMap<>();

    @Override
    public String toString() {
        return "MenuTree{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", text='" + text + '\'' +
                ", state='" + state + '\'' +
                ", url='" + url + '\'' +
                ", children=" + children +
                ", attributes=" + attributes +
                '}';
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
