package aut.bme.hu.configurator.core.service;

import aut.bme.hu.configurator.core.config.ProtocolConfig;
import aut.bme.hu.configurator.core.dto.ComplexConfigResult;
import aut.bme.hu.configurator.core.dto.CreateConfigMessage;
import aut.bme.hu.configurator.core.dto.GetConfigsByIdsMessage;
import aut.bme.hu.configurator.core.dto.UpdateConfigMessage;
import aut.bme.hu.configurator.core.model.Config;
import aut.bme.hu.configurator.core.repository.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfiguratorService {

    private final ProtocolConfig protocolConfig;
    private final ConfigRepository configRepository;

    public Config getConfig() {
        return null;
        // TODO - implement
    }

    public List<Config> getAllConfigs() {
        return new ArrayList<>();
        // TODO - implement
    }

    public void createConfig(CreateConfigMessage message) {
        // TODO - implement
    }

    public void updateConfig(UpdateConfigMessage message) {
        // TODO - implement
    }

    public void removeConfig(String id) {
        // TODO - implement
    }

    public List<ComplexConfigResult> getConfigsByIds(GetConfigsByIdsMessage message) {
        return new ArrayList<>();
        // TODO - implement
    }

}
