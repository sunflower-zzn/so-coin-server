package com.zhuonan;

import com.zhuonan.controller.Neo4jController;
import com.zhuonan.model.Neo4jNode;
import com.zhuonan.model.NodeQueryArgs;
import com.zhuonan.utils.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author zhuonan
 * @Date 2021/12/18
 * @Description
 */
@SpringBootTest
public class NodeTests {
    @Autowired
    Neo4jController controller;

    @Test
    void QueryNodeTest0() {
        NodeQueryArgs args = new NodeQueryArgs();
        ResponseResult response = controller.queryNode(args);
        List queryList = (List) response.getContent();
        Assert.isTrue(queryList.size() == 0, "");
    }

    @Test
    void QueryNodeTest1() {
        NodeQueryArgs args = new NodeQueryArgs();
        long id = 129284L;
        args.setId(id);
        ResponseResult response = controller.queryNode(args);
        List queryList = (List) response.getContent();
        Assert.isTrue(queryList.size() == 1, "");
        Assert.isTrue(((Neo4jNode) queryList.get(0)).getId() == id, "");
    }

    @Test
    void QueryNodeTest2() {
        NodeQueryArgs args = new NodeQueryArgs();
        String tag = "tag";
        String name = "java";
        args.setLabel(tag);
        args.setName(name);
        // 精确查询
        args.setIsLikeName(false);
        ResponseResult response = controller.queryNode(args);
        List queryList = (List) response.getContent();
        Assert.isTrue(queryList.size() == 1, "");
        Assert.isTrue(((Neo4jNode) queryList.get(0)).getProperties().get("name").equals(name), "");
        Assert.isTrue(((Neo4jNode) queryList.get(0)).getLabels().contains(tag), "");
    }

    @Test
    void QueryNodeTest3() {
        NodeQueryArgs args = new NodeQueryArgs();
        String tag = "tag";
        String name = "java";
        args.setLabel(tag);
        args.setName(name);
        // 模糊查询
        args.setIsLikeName(true);
        ResponseResult response = controller.queryNode(args);
        List queryList = (List) response.getContent();
        Assert.isTrue(queryList.size() > 1, "");
        for (Object node : queryList) {
            Assert.isTrue(((String) ((Neo4jNode) node).getProperties().get("name")).contains(name), "");
            Assert.isTrue(((Neo4jNode) node).getLabels().contains(tag), "");
        }
    }

    @Test
    void QueryNodeTest4() {
        NodeQueryArgs args = new NodeQueryArgs();
        String tag = "tag";
        String name = "java";
        args.setLabel(tag);
        args.setName(name);
        args.setIsLikeName(true);
        Integer limit = 3;
        args.setLimit(limit);
        ResponseResult response = controller.queryNode(args);
        List queryList = (List) response.getContent();
        Assert.isTrue(queryList.size() == limit, "");
    }
}
