package org.sid.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    /*@Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder){
        //si on connait où se trouve les MS
        return builder.routes()
                .route((r)->r.path("/customers/**").uri("http://localhost:8081"))
                .route((r)->r.path("/products/**").uri("http://localhost:8082"))
                .route((r)->r.path("/login" , "/auth/**","/users/**","/users").uri("http://localhost:8089"))
                .route((r)->r.path("/m1/**").uri("http://localhost:8095"))
                .build();

        si je connait les noms des MS
        return builder.routes()
                .route((r)->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))   //à chaque fois que tu reçois une requete qui contient /customers/** envoie cette requete à une service qui s'appel customer-service , je suis pas obligé de connaitre la machine où se trouve
                .route((r)->r.path("/products/**").uri("lb://PRODUCT-SERVICE"))
                .build();

    }*/


    //à chaque fois que tu reçoit une requte, regarde dans url de la requete ,tu vas trouver le nom du MS
    //http://localhost:8888/CUSTOMER-SERVICE/customers
    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(
            ReactiveDiscoveryClient rdc,
            DiscoveryLocatorProperties properties
    ){
        return new DiscoveryClientRouteDefinitionLocator(rdc , properties);
    }

}
