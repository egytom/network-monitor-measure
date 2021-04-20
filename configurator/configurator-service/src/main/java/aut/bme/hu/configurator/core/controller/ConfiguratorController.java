package aut.bme.hu.configurator.core.controller;

import aut.bme.hu.configurator.api.message.*;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapper;
import aut.bme.hu.configurator.core.service.ConfiguratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class ConfiguratorController implements IConfiguratorController {

    private final ConfiguratorService configuratorService;
    private final ConfiguratorMapper configuratorMapper;

    @Override
    public ConfigResponse getConfig(String id) {
        log.debug("-----> GET Config request through /configurator/configs/" + id);
        try {
            return configuratorMapper.toResponse(configuratorService.getConfig(id));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Config not found.");
        }
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
    public void createConfig(CreateConfigRequest request) {
        log.debug("-----> POST Config request through /configurator/configs/");
        configuratorService.createConfig(configuratorMapper.toMessage(request));
    }

    @Override
    public void updateConfig(UpdateConfigRequest request) {
        log.debug("-----> PUT Config request through /configurator/configs/");
        try {
            configuratorService.updateConfig(configuratorMapper.toMessage(request));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Config not found.");
        }
    }

    @Override
    public void removeConfig(String id) {
        log.debug("-----> DELETE Config request through /configurator/configs/");
        configuratorService.removeConfig(id);
    }

    @Override
    public List<ComplexConfigElement> getConfigsByIds(GetConfigsByIdsRequest request) {
        log.debug("-----> GET Configs by IDs request through /configurator/configs/ids");
        return configuratorService.getConfigsByIds(configuratorMapper.toMessage(request))
                .stream()
                .map(configuratorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ComplexConfigResponse getComplexConfig(@PathVariable String complexId) {
        log.debug("-----> GET ComplexConfig request through /configurator/complex/configs/" + complexId);
        try {
            return configuratorService.getComplexConfig(complexId);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ComplexConfig not found.");
        }
    }

    @Override
    public List<ComplexConfigResponse> getAllComplexConfigs() {
        log.debug("-----> GET all ComplexConfig request through /configurator/complex/configs/");
        return configuratorService.getAllComplexConfigs();
    }

    @Override
    public void createComplexConfig(CreateComplexConfigRequest request) {
        log.debug("-----> POST ComplexConfig request through /configurator/complex/configs/");
        configuratorService.createComplexConfig(configuratorMapper.toMessage(request));
    }

    @Override
    public void updateComplexConfig(UpdateComplexConfigRequest request) {
        log.debug("-----> PUT ComplexConfig request through /configurator/complex/configs/");
        try {
            configuratorService.updateComplexConfig(configuratorMapper.toMessage(request));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Config not found.");
        }
    }

    @Override
    public void removeComplexConfig(String complexId) {
        log.debug("-----> DELETE ComplexConfig request through /configurator/complex/configs/");
        configuratorService.removeComplexConfig(complexId);
    }

}
