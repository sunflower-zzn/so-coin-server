package com.zhuonan.model;

/**
 * @Author zhuonan
 * @Date 2021/9/12
 * @Description
 */
public class Neo4jRelation extends GraphObject {
    /**
     * type 类别
     */
    private String type;

    /**
     * start node
     */
    private Neo4jNode start;

    /**
     * end node
     */
    private Neo4jNode end;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Neo4jNode getStart() {
        return start;
    }

    public void setStart(Neo4jNode start) {
        this.start = start;
    }

    public Neo4jNode getEnd() {
        return end;
    }

    public void setEnd(Neo4jNode end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "关系Relation：[id:" + getId() + ", label:" + getType() + ", properties:" + getProperties().toString() + "]";
    }

}
