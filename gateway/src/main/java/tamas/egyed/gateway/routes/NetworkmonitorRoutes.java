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
                        .filters(f -> {
                            f.addResponseHeader("Access-Control-Allow-Origin", "*");
                            f.addResponseHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
                            f.addResponseHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With, Accept");
                            return f;
                        })
                        .uri("lb://networkmonitor-service/")
                        .id("networkmonitor-service"))
                .build();
    }

}
