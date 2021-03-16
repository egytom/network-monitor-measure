package aut.bme.hu.configurator.api.feign;

import aut.bme.hu.configurator.api.message.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("configurator-service")
public interface ConfiguratorServiceFeignClient {

    @GetMapping("/configurator/configs/{id}")
    ConfigResponse getConfig(@PathVariable String id);

    @GetMapping("/configurator/configs")
    List<ConfigResponse> getAllConfigs();

    @PostMapping("/configurator/configs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void createConfig(@RequestBody CreateConfigRequest request);

    @PutMapping("/configurator/configs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateConfig(@RequestBody UpdateConfigRequest request);

    @DeleteMapping("/configurator/configs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeConfig(@PathVariable String id);

    @PostMapping("/configurator/configs/ids")
    List<ComplexConfigResponse> getConfigsByIds(@RequestBody GetConfigsByIdsRequest request);

}
