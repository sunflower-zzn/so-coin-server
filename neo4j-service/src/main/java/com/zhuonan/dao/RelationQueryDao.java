package com.zhuonan.dao;

import com.zhuonan.model.Neo4jRelation;
import com.zhuonan.model.RelationQueryArgs;
import com.zhuonan.utils.StatementResultUtils;
import org.apache.commons.lang.StringUtils;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhuonan
 * @Date 2021/12/5
 * @Description 关系以及出入节点查询
 */
@Repository
public class RelationQueryDao {
    private static final String TYPE_RELATED_TAG = "related";
    private static final String TYPE_RELATED_QUESTION = "relatedQuestion";
    private static final String CYPHER_RELATED_TAG = "MATCH (start)-[r:related]->(end) %s RETURN start,r,end ORDER BY r.numberOfReference SKIP %d LIMIT %d;";
    private static final String CYPHER_RELATED_QUESTION = "MATCH (start)-[r:relatedQuestion]->(end) %s RETURN start,r,end SKIP %d LIMIT %d;";

    @Autowired
    Session session;

    public List<Neo4jRelation> query(RelationQueryArgs args) {
        StatementResult result = session.run(buildCypher(args));
        return StatementResultUtils.buildRelationList(result);
    }

    private String buildCypher(RelationQueryArgs args) {
        String cypherSql = "MATCH (start)-[r]->(end) return start,r,end LIMIT 0;"; // type不为related或relatedQuestion时候返回空结果
        // name构建where条件
        String name;
        if (args.getStartName() == null || args.getStartName().isEmpty()) {
            name = StringUtils.isBlank(args.getEndName()) ? "" : String.format("WHERE end.name = '%s' ", args.getEndName());
        } else {
            List<String> nameList = args.getStartName().stream().map(s -> "'" + s + "'").collect(Collectors.toList());
            if (StringUtils.isNotBlank(args.getEndName())) {
                name = String.format("WHERE start.name IN %s AND end.name = '%s'", nameList, args.getEndName());

            } else {
                name = String.format("WHERE start.name IN %s ", nameList);
            }
        }
        // 拼接cypherSQL
        if (StringUtils.equals(args.getRelationType(), TYPE_RELATED_TAG)) // 检索tag
            cypherSql = String.format(CYPHER_RELATED_TAG, name, args.getOffset(), args.getLimit());
        else if (StringUtils.equals(args.getRelationType(), TYPE_RELATED_QUESTION))
            cypherSql = String.format(CYPHER_RELATED_QUESTION, name, args.getOffset(), args.getLimit());
        // 打印cypher
        System.out.println("[neo4j-relationQuery]:" + cypherSql);
        return cypherSql;
    }
}
