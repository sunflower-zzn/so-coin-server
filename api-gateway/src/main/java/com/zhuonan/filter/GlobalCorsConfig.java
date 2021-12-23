package com.zhuonan.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

/**
 * @Author zhuonan
 * @Date 2021/11/30
 * @Description GateWay 服务端跨域配置，添加header
 */
@Configuration
public class GlobalCorsConfig {
    private static final String MAX_AGE = "18000L";

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                HttpHeaders requestHeaders = request.getHeaders();
                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();
                // 允许跨域的源(网站域名/ip)，设置*为全部
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
                // 允许跨域请求里的header字段，设置*为全部
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
                // 允许携带认证信息
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                // 前端需要放在请求头中的值,例如一些oauth的Authorization参数
                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "token");
                // 允许携带认证信息
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST");
                // 跨域允许的有效期
                headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
            }
            System.out.printf("全局过滤器：当前请求的url:{%s}, method:{%s}%n", request.getURI().getPath(), request.getMethodValue());
            return chain.filter(ctx);
        };
    }
}

