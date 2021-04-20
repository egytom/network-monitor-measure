package aut.bme.hu.configurator.core.controller;

import aut.bme.hu.configurator.api.message.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public interface IConfiguratorController {

    @GetMapping("/configs/{id}")
    ConfigResponse getConfig(@PathVariable String id);

    @GetMapping("/configs")
    List<ConfigResponse> getAllConfigs();

    @PostMapping("/configs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void createConfig(@Valid @RequestBody CreateConfigRequest request);

    @PutMapping("/configs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateConfig(@Valid @RequestBody UpdateConfigRequest request);

    @DeleteMapping("/configs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeConfig(@PathVariable String id);

    @PostMapping("/configs/ids")
    List<ComplexConfigElement> getConfigsByIds(@Valid @RequestBody GetConfigsByIdsRequest request);

    @GetMapping("/complex/configs/{complexId}")
    ComplexConfigResponse getComplexConfig(@PathVariable String complexId);

    @GetMapping("/complex/configs")
    List<ComplexConfigResponse> getAllComplexConfigs();

    @PostMapping("/complex/configs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void createComplexConfig(@Valid @RequestBody CreateComplexConfigRequest request);

    @PutMapping("/complex/configs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateComplexConfig(@Valid @RequestBody UpdateComplexConfigRequest request);

    @DeleteMapping("/complex/configs/{complexId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeComplexConfig(@PathVariable String complexId);

}
