package com.cartaocore.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("usuario", r -> r
                        .path("/usuario")
                        .uri("http://localhost:8080"))
                .route("usuario", r -> r
                        .path("/usuario/**")
                        .uri("http://localhost:8080"))
                .route("cartao", r -> r
                        .path("/cartao")
                        .uri("http://localhost:8083"))
                .build();
    }
}
