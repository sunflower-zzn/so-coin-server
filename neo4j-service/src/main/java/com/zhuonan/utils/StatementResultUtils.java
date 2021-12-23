package com.zhuonan.utils;

import com.zhuonan.model.Neo4jNode;
import com.zhuonan.model.Neo4jRelation;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Author zhuonan
 * @Date 2021/9/15
 * @Description
 */
public class StatementResultUtils {

    /**
     * 处理cypherSql语句的返回值StatementResult，是一个键值对，StatementResult是一个Record对象的集合
     *
     * @param result result
     * @return 节点列表
     */
    public static List<Neo4jNode> buildNodeList(StatementResult result) {
        List<Neo4jNode> nodeList = new ArrayList<>();
        while (result.hasNext()) {
            // record:(n)
            Record record = result.next();
            Node recordNode = record.get("n").asNode();
            nodeList.add(buildMyNode(recordNode));
        }
        return nodeList;
    }

    /**
     * 处理cypherSql语句的返回值StatementResult，是一个键值对，StatementResult是一个Record对象的集合
     *
     * @param result result
     * @return 关系列表
     */
    public static List<Neo4jRelation> buildRelationList(StatementResult result) {
        List<Neo4jRelation> relationList = new ArrayList<>();
        while (result.hasNext()) {
            // record:(start)-[r]->(end)
            Record record = result.next();
            Node startNode = record.get("start").asNode();
            Relationship recordRelation = record.get("r").asRelationship();
            Node endNode = record.get("end").asNode();
            relationList.add(buildMyRelation(startNode, recordRelation, endNode));
        }
        return relationList;
    }

    private static Neo4jNode buildMyNode(Node recordNode) {
        Neo4jNode node = new Neo4jNode();
        node.setId(recordNode.id());
        List<String> labels = new ArrayList<>();
        recordNode.labels().forEach(labels::add);
        node.setLabels(labels);
        node.setProperties(strOfMapToList(recordNode.asMap(), "tags"));
        return node;
    }

    // 把map中的str转化为list
    private static Map<String, Object> strOfMapToList(Map<String, Object> map, String... args) {
        // 注意要clone新map，否则不能操作
        Map<String, Object> properties = new HashMap<>(map);
        for (String arg : args) {
            if (properties.containsKey(arg)) {
                String tagsStr = (String) properties.get(arg);
                tagsStr = StringUtils.deleteAny(tagsStr, "' []");
                if (tagsStr.length() > 0) {
                    List<String> tagsList = new ArrayList<>(Arrays.asList(tagsStr.split(",")));
                    properties.put(arg, tagsList);
                }
            }
        }
        return properties;
    }

    private static Neo4jRelation buildMyRelation(Node startNode, Relationship recordRelation, Node endNode) {
        Neo4jRelation relation = new Neo4jRelation();
        relation.setId(recordRelation.id());
        relation.setType(recordRelation.type());
        relation.setProperties(recordRelation.asMap());
        relation.setStart(buildMyNode(startNode));
        relation.setEnd(buildMyNode(endNode));
        return relation;
    }
}
