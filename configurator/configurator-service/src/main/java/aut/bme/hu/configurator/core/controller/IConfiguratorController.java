package aut.bme.hu.configurator.core.controller;

import aut.bme.hu.configurator.api.message.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface IConfiguratorController {

    @GetMapping("/configs/{id}")
    ConfigResponse getConfig(@PathVariable String id);

    @GetMapping("/configs")
    List<ConfigResponse> getAllConfigs();

    @PostMapping("/configs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void createConfig(@RequestBody CreateConfigRequest request);

    @PutMapping("/configs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateConfig(@RequestBody UpdateConfigRequest request);

    @DeleteMapping("/configs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeConfig(@PathVariable String id);

    @PostMapping("/configs/ids")
    List<ComplexConfigResponse> getConfigsByIds(@RequestBody GetConfigsByIdsRequest request);

}
