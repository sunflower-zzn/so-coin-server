package com.zhuonan.service;

import com.zhuonan.dao.NodeQueryDao;
import com.zhuonan.model.Neo4jNode;
import com.zhuonan.model.NodeQueryArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhuonan
 * @Date 2021/11/30
 * @Description
 */
@Service
public class NodeQueryService {
    @Autowired
    NodeQueryDao nodeQueryDao;

    public List<Neo4jNode> nodeQuery(NodeQueryArgs args) {
        return nodeQueryDao.query(args);
    }

    public int nodeCount(NodeQueryArgs args) {
        return nodeQueryDao.query(args).size();
    }
}
