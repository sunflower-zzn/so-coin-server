package com.zhuonan.dao;

import com.zhuonan.model.Neo4jNode;
import com.zhuonan.model.NodeQueryArgs;
import com.zhuonan.utils.StatementResultUtils;
import org.apache.commons.lang.StringUtils;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhuonan
 * @Date 2021/11/30
 * @Description
 */
@Repository
public class NodeQueryDao {
    private static final String LABEL_TAG = "tag";
    private static final String LABEL_QUESTION = "question";
    private static final String CYPHER_QUERY_ID = "MATCH (n) WHERE ID(n)=%d RETURN n";
    private static final String CYPHER_TAG = "MATCH (n:tag) WHERE n.name %s RETURN n ORDER BY n.numberOfQuestions DESC SKIP %d LIMIT %d;";
    private static final String CYPHER_QUESTION = "MATCH (n:question) WHERE n.name %s RETURN n SKIP %d LIMIT %d;";

    @Autowired
    Session session;

    public List<Neo4jNode> query(NodeQueryArgs args) {
        StatementResult result = session.run(buildCypher(args));
        return StatementResultUtils.buildNodeList(result);
    }

    private String buildCypher(NodeQueryArgs args) {
        String cypherSql = "MATCH (n) RETURN n LIMIT 0";    // label不为tag或question时候返回空结果
        // 有id直接按照id查询
        if (args.getId() != null) {
            cypherSql = String.format(CYPHER_QUERY_ID, args.getId());
        }
        // 没有id根据其他条件拼接sql
        else {
            // name 精准查询or模糊查询
            String name = args.getIsLikeName()  // 模糊查询使用 CONTAINS
                    ? String.format("CONTAINS '%s'", args.getName())
                    : String.format("= '%s'", args.getName());
            if (StringUtils.isBlank(args.getName())) name = "CONTAINS ''";  // name字段为空，模糊查询‘’即可
            // 拼接cypherSQL
            if (StringUtils.equals(args.getLabel(), LABEL_TAG)) // 检索tag
                cypherSql = String.format(CYPHER_TAG, name, args.getOffset(), args.getLimit());
            else if (StringUtils.equals(args.getLabel(), LABEL_QUESTION))
                cypherSql = String.format(CYPHER_QUESTION, name, args.getOffset(), args.getLimit());
        }
        System.out.println("[neo4j-nodeQuery]:" + cypherSql);
        return cypherSql;
    }
}
