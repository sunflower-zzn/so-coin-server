package com.zhuonan.model;

import java.util.List;

/**
 * @Author zhuonan
 * @Date 2021/12/5
 * @Description
 */
public class RelationQueryArgs {
    private List<String> startName;
    private String relationType;
    private String endName;
    private Integer offset;
    private Integer limit;

    public RelationQueryArgs() {
        this.startName = null;
        this.relationType = null;
        this.endName = null;
        this.offset = 0;
        this.limit = 100;
    }

    public List<String> getStartName() {
        return startName;
    }

    public void setStartName(List<String> startName) {
        this.startName = startName;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "startName=" + this.startName + ";type=" + this.relationType + ";endName=" + this.endName +
                ";offset=" + this.offset + ";limit=" + limit;
    }
}
