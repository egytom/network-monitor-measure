package aut.bme.hu.configurator.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("configurator-service")
public interface ConfiguratorServiceFeignClient {

    @GetMapping("/configurator/world")
    String getWorld();

}
