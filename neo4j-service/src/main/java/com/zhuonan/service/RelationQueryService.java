package com.zhuonan.service;

import com.zhuonan.dao.RelationQueryDao;
import com.zhuonan.model.Neo4jRelation;
import com.zhuonan.model.RelationQueryArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhuonan
 * @Date 2021/12/5
 * @Description
 */
@Service
public class RelationQueryService {
    @Autowired
    RelationQueryDao relationQueryDao;

    public List<Neo4jRelation> relationQuery(RelationQueryArgs args) {
        List<Neo4jRelation> res = relationQueryDao.query(args);
        return removeDoubleRelation(args, res);
    }

    public int relationCount(RelationQueryArgs args) {
        List<Neo4jRelation> res = relationQueryDao.query(args);
        return removeDoubleRelation(args, res).size();
    }

    // 去除两个节点间可能存在的复数条关系（保证至多只有一条）
    private List<Neo4jRelation> removeDoubleRelation(RelationQueryArgs args, List<Neo4jRelation> list) {
        List<Neo4jRelation> resList = new ArrayList<>();
        List<String> startNameList = args.getStartName();
        if (startNameList == null) return list;
        boolean[][] matrix = new boolean[startNameList.size()][startNameList.size()];
        for (Neo4jRelation relation : list) {
            String startName = (String) relation.getStart().getProperties().get("name");
            String endName = (String) relation.getEnd().getProperties().get("name");
            if (startNameList.contains(startName) && startNameList.contains(endName)) {
                int x = startNameList.indexOf(startName);
                int y = startNameList.indexOf(endName);
                if (!matrix[x][y] && !matrix[y][x]) {
                    matrix[x][y] = true;
                    resList.add(relation);
                }
            } else resList.add(relation);
        }
        return resList;
    }
}
