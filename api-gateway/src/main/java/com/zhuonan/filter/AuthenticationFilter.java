package com.zhuonan.filter;

import com.zhuonan.model.LoginUser;
import com.zhuonan.model.TokenAuth;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author zhuonan
 * @Date 2021/12/4
 * @Description 身份认证
 */
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory {

    @Autowired
    private TokenAuth tokenAuth;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // 0. 放行注册和登录请求
            String path = request.getPath().toString();
            if (path.equals("/user-service/user/login") || path.equals("/user-service/user/register")) {
                ServerHttpRequest host = exchange.getRequest().mutate().build();
                ServerWebExchange build = exchange.mutate().request(host).build();
                return chain.filter(build);
            }

            // 1. 获取token
            String token = request.getHeaders().getFirst("token");

            System.out.println(String.format("局部过滤器：当前请求的url:{%s}, method:{%s}",
                    request.getURI().getPath(), request.getMethodValue()));

            if (Strings.isEmpty(token)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            // 2. 验证用户是否已登陆
            LoginUser loginUser = tokenAuth.getSession(token);
            if (loginUser == null) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            // 3. 将用户id传递给后端服务
            ServerWebExchange build;
            ServerHttpRequest host = exchange.getRequest().mutate()
                    .header("X-User-Id", String.valueOf(loginUser.getUserId()))
                    .build();
            build = exchange.mutate().request(host).build();

            return chain.filter(build);
        };
    }
}