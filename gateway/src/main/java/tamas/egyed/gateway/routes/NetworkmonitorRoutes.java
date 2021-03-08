package tamas.egyed.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NetworkmonitorRoutes {

    @Bean
    public RouteLocator networkmonitorRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/networkmonitor/**")
                        .uri("lb://networkmonitor-service/")
                        .id("networkmonitor-service"))
                .build();
    }

}
