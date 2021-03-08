package tamas.egyed.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguratorRoutes {

    @Bean
    public RouteLocator configuratorRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/configurator/**")
                        .uri("lb://configurator-service/")
                        .id("configurator-service"))
                .build();
    }

}
