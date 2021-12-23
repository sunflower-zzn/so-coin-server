package com.zhuonan.model;

import java.util.List;

/**
 * @Author zhuonan
 * @Date 2021/9/12
 * @Description
 */
public class Neo4jNode extends GraphObject {
    /**
     * 标签（用于划分类别）
     */
    private List<String> labels;

    public Neo4jNode() {
        super();
    }

    public Neo4jNode(Long id) {
        super();
        this.setId(id);
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "节点Node：[id:" + getId() + ", label:" + getLabels() + ", properties:" + getProperties().toString() + "]";
    }
}
