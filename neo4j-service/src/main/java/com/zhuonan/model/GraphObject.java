package com.zhuonan.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhuonan
 * @Date 2021/9/12
 * @Description 图数据库对象基本结构，节点和关系都集成自此实体类
 */
public class GraphObject {
    /**
     * 唯一标识id
     */
    private Long id;

    /**
     * 属性（键值对），约束：一定要有name字段！
     */
    private Map<String, Object> properties;

    public GraphObject() {
        properties = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
