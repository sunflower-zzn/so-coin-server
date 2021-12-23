package com.zhuonan.model;

/**
 * @Author zhuonan
 * @Date 2021/12/5
 * @Description 收藏节点DO
 */
public class FavoriteBean {
    private Integer id;
    private Integer userId;
    private String nodeLabel;
    private String nodeName;

    public FavoriteBean(Integer id, Integer userId, String nodeLabel, String nodeName) {
        this.id = id;
        this.userId = userId;
        this.nodeLabel = nodeLabel;
        this.nodeName = nodeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNodeLabel() {
        return nodeLabel;
    }

    public void setNodeLabel(String nodeLabel) {
        this.nodeLabel = nodeLabel;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
