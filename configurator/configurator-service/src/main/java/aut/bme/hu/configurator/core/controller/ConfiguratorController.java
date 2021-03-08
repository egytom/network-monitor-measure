package aut.bme.hu.configurator.core.controller;

import aut.bme.hu.configurator.core.service.ConfiguratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ConfiguratorController implements IConfiguratorController {

    private final ConfiguratorService configuratorService;

    @Override
    public String getWorld() {
        log.info("-----> GetWorld request through /configurator/world");
        return configuratorService.getWorld();
    }

}
