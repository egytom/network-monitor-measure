package aut.bme.hu.configurator.core.service;

import aut.bme.hu.configurator.api.message.Protocol;
import aut.bme.hu.configurator.core.config.ProtocolConfig;
import aut.bme.hu.configurator.core.dto.ComplexConfigResult;
import aut.bme.hu.configurator.core.dto.CreateConfigMessage;
import aut.bme.hu.configurator.core.dto.GetConfigsByIdsMessage;
import aut.bme.hu.configurator.core.dto.UpdateConfigMessage;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapper;
import aut.bme.hu.configurator.core.model.Config;
import aut.bme.hu.configurator.core.repository.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConfiguratorService {

    private final ProtocolConfig protocolConfig;
    private final ConfigRepository configRepository;
    private final ConfiguratorMapper configuratorMapper;

    public Config getConfig(String id) {
        return configRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Config> getAllConfigs() {
        return configRepository.findAll();
    }

    public void createConfig(CreateConfigMessage message) {
        setConfigProtocolToDefaultIfEmpty(message);
        Config config = configuratorMapper.toConfig(message);
        configRepository.save(config);
    }

    public void updateConfig(UpdateConfigMessage message) {
        Config configToUpdate = configRepository
                .findById(message.getId())
                .orElseThrow(EntityNotFoundException::new);
        Config configToSave = updateConfigWithMessageFields(configToUpdate, message);
        configRepository.save(configToSave);
    }

    public void removeConfig(String id) {
        configRepository.deleteById(id);
    }

    public List<ComplexConfigResult> getConfigsByIds(GetConfigsByIdsMessage message) {
        List<Config> configList = configRepository.findAllById(message.getIds());
        List<ComplexConfigResult> resultList = configList
                .stream()
                .map(configuratorMapper::toResult)
                .collect(Collectors.toList());

        setSequenceNumberForComplexConfigList(message, resultList);
        return resultList;
    }

    private void setConfigProtocolToDefaultIfEmpty(CreateConfigMessage message) {
        if (message.getProtocol() == null) {
            message.setProtocol(
                    Protocol.valueOf(
                            protocolConfig.getDefaultType()
                    )
            );
        }
    }

    private Config updateConfigWithMessageFields(Config config, UpdateConfigMessage message) {
        if (message.getCategory() != null) {
            config.setCategory(message.getCategory());
        }

        if (message.getDurationInSec() > 0) {
            config.setDurationInSec(message.getDurationInSec());
        }

        if (message.getName() != null) {
            config.setName(message.getName());
        }

        if (message.getProtocol() != null) {
            config.setProtocol(message.getProtocol());
        }

        return config;
    }

    private void setSequenceNumberForComplexConfigList(GetConfigsByIdsMessage message, List<ComplexConfigResult> resultList) {
        int sequenceNumber = 1;
        for (String id : message.getIds()) {
            Optional<ComplexConfigResult> complexConfigResultOptional = resultList
                    .stream()
                    .filter(r -> r.getId().equalsIgnoreCase(id))
                    .findFirst();

            if (complexConfigResultOptional.isPresent()) {
                complexConfigResultOptional.get().setSequenceNumber(sequenceNumber++);
            }
        }
    }

}
