package com.zhuonan;

import com.zhuonan.controller.Neo4jController;
import com.zhuonan.model.Neo4jRelation;
import com.zhuonan.model.RelationQueryArgs;
import com.zhuonan.utils.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhuonan
 * @Date 2021/12/18
 * @Description
 */
@SpringBootTest
public class RelationTests {
    @Autowired
    Neo4jController controller;

    @Test
    void RelationQueryTest0() {
        RelationQueryArgs args = new RelationQueryArgs();
        ResponseResult response = controller.queryRelation(args);
        List queryList = (List) response.getContent();
        Assert.isTrue(queryList.size() == 0, "");
    }

    @Test
    void RelationQueryTest1() {
        RelationQueryArgs args = new RelationQueryArgs();
        String type = "related";
        String sName1 = "java";
        List<String> startName = new ArrayList<>();
        startName.add(sName1);
        args.setRelationType(type);
        args.setStartName(startName);
        ResponseResult response = controller.queryRelation(args);
        List queryList = (List) response.getContent();
        Assert.isTrue(queryList.size() > 0, "");
        for (Object obj : queryList) {
            Neo4jRelation relation = (Neo4jRelation) obj;
            Assert.isTrue(relation.getType().equals(type), "");
            Assert.isTrue(relation.getStart().getProperties().get("name").equals(sName1), "");
        }
    }

    @Test
    void RelationQueryTest2() {
        RelationQueryArgs args = new RelationQueryArgs();
        String type = "related";
        String sName1 = "java";
        String sName2 = "javascript";
        List<String> startName = new ArrayList<>();
        startName.add(sName1);
        startName.add(sName2);
        args.setRelationType(type);
        args.setStartName(startName);
        ResponseResult response = controller.queryRelation(args);
        List queryList = (List) response.getContent();
        Assert.isTrue(queryList.size() > 0, "");
        for (Object obj : queryList) {
            Neo4jRelation relation = (Neo4jRelation) obj;
            Assert.isTrue(relation.getType().equals(type), "");
            Assert.isTrue(startName.contains(relation.getStart().getProperties().get("name")), "");
        }
    }

    @Test
    void RelationQueryTest3() {
        RelationQueryArgs args = new RelationQueryArgs();
        String type = "related";
        String endName = "java";
        args.setRelationType(type);
        args.setEndName(endName);
        ResponseResult response = controller.queryRelation(args);
        List queryList = (List) response.getContent();
        Assert.isTrue(queryList.size() > 0, "");
        for (Object obj : queryList) {
            Neo4jRelation relation = (Neo4jRelation) obj;
            Assert.isTrue(relation.getType().equals(type), "");
            Assert.isTrue(relation.getEnd().getProperties().get("name").equals(endName), "");
        }
    }

    @Test
    void RelationQueryTest4() {
        RelationQueryArgs args = new RelationQueryArgs();
        String type = "related";
        String sName1 = "java";
        Integer limit = 10;
        List<String> startName = new ArrayList<>();
        startName.add(sName1);
        args.setRelationType(type);
        args.setStartName(startName);
        args.setLimit(limit);
        ResponseResult response = controller.queryRelation(args);
        List queryList = (List) response.getContent();
        Assert.isTrue(queryList.size() == limit, "");
        for (Object obj : queryList) {
            Neo4jRelation relation = (Neo4jRelation) obj;
            Assert.isTrue(relation.getType().equals(type), "");
            Assert.isTrue(relation.getStart().getProperties().get("name").equals(sName1), "");
        }
    }
}
