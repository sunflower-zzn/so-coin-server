package com.zhuonan.model;

/**
 * @Author zhuonan
 * @Date 2021/11/30
 * @Description
 */
public class NodeQueryArgs {
    private Long id;
    private String label;
    private String name;
    private Boolean isLikeName;
    private Integer offset;
    private Integer limit;

    public NodeQueryArgs() {
        this.id = null;
        this.label = null;
        this.name = null;
        this.isLikeName = false;
        this.offset = 0;
        this.limit = 100;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsLikeName() {
        return isLikeName;
    }

    public void setIsLikeName(Boolean isLikeName) {
        this.isLikeName = isLikeName;
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
        return "id=" + this.id + ";label=" + this.label + ";name=" + this.name + ";isLikeName" + this.isLikeName
                + ";offset=" + this.offset + ";limit=" + limit;
    }
}
