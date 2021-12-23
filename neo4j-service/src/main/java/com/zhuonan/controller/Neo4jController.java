package com.zhuonan.controller;

import com.zhuonan.model.NodeQueryArgs;
import com.zhuonan.model.RelationQueryArgs;
import com.zhuonan.service.NodeQueryService;
import com.zhuonan.service.RelationQueryService;
import com.zhuonan.utils.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhuonan
 * @Date 2021/9/15
 * @Description
 */
@RestController
public class Neo4jController {
    private static final Logger logger = LoggerFactory.getLogger(Neo4jController.class);

    @Autowired
    NodeQueryService nodeQueryService;

    @Autowired
    RelationQueryService relationQueryService;

    @RequestMapping(value = "/node/query", method = RequestMethod.POST)
    public ResponseResult queryNode(@RequestBody NodeQueryArgs args) {
        try {
            logger.info("[neo4j-service:queryNode]" + args.toString());
            return ResponseResult.buildSuccess(nodeQueryService.nodeQuery(args));
        } catch (Exception e) {
            logger.info("[ERROR][neo4j-service:queryNode]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }

    @RequestMapping(value = "/node/count", method = RequestMethod.POST)
    public ResponseResult countNode(@RequestBody NodeQueryArgs args) {
        try {
            logger.info("[neo4j-service:countNode]" + args.toString());
            return ResponseResult.buildSuccess(nodeQueryService.nodeCount(args));
        } catch (Exception e) {
            logger.info("[ERROR][neo4j-service:countNode]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }

    @RequestMapping(value = "/relation/query", method = RequestMethod.POST)
    public ResponseResult queryRelation(@RequestBody RelationQueryArgs args) {
        try {
            logger.info("[neo4j-service:queryRelation]" + args.toString());
            return ResponseResult.buildSuccess(relationQueryService.relationQuery(args));
        } catch (Exception e) {
            logger.info("[ERROR][neo4j-service:queryRelation]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }

    @RequestMapping(value = "/relation/count", method = RequestMethod.POST)
    public ResponseResult countRelation(@RequestBody RelationQueryArgs args) {
        try {
            logger.info("[neo4j-service:countRelation]" + args.toString());
            return ResponseResult.buildSuccess(relationQueryService.relationCount(args));
        } catch (Exception e) {
            logger.info("[ERROR][neo4j-service:countRelation]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }
}
