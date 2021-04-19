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
                        .filters(f -> {
                            f.addResponseHeader("Access-Control-Allow-Origin", "*");
                            f.addResponseHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
                            f.addResponseHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With, Accept");
                            return f;
                        })
                        .uri("lb://configurator-service/")
                        .id("configurator-service"))
                .build();
    }

}
