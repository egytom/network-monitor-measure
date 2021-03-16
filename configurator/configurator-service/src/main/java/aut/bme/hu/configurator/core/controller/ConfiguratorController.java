package aut.bme.hu.configurator.core.controller;

import aut.bme.hu.configurator.api.message.*;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapper;
import aut.bme.hu.configurator.core.service.ConfiguratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ConfiguratorController implements IConfiguratorController {

    private final ConfiguratorService configuratorService;
    private final ConfiguratorMapper configuratorMapper;

    @Override
    public ConfigResponse getConfig(String id) {
        log.debug("-----> GET Config request through /configurator/configs/" + id);
        return configuratorMapper.toResponse(configuratorService.getConfig());
    }

    @Override
    public List<ConfigResponse> getAllConfigs() {
        log.debug("-----> GET all Config request through /configurator/configs/");
        return configuratorService.getAllConfigs()
                .stream()
                .map(configuratorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void createConfig(@Valid @RequestBody CreateConfigRequest request) {
        log.debug("-----> POST config request through /configurator/configs/");
        configuratorService.createConfig(configuratorMapper.toDTO(request));
    }

    @Override
    public void updateConfig(@Valid @RequestBody UpdateConfigRequest request) {
        log.debug("-----> PUT Config request through /configurator/configs/");
        configuratorService.updateConfig(configuratorMapper.toDTO(request));
    }

    @Override
    public void removeConfig(String id) {
        log.debug("-----> DELETE Config request through /configurator/configs/");
        configuratorService.removeConfig(id);
    }

    @Override
    public List<ComplexConfigResponse> getConfigsByIds(@Valid @RequestBody GetConfigsByIdsRequest request) {
        log.debug("-----> GET Configs by IDs request through /configurator/configs/ids");
        return configuratorService.getConfigsByIds(configuratorMapper.toDTO(request))
                .stream()
                .map(configuratorMapper::toResponse)
                .collect(Collectors.toList());
    }

}
