package aut.bme.hu.configurator.core.service;

import aut.bme.hu.configurator.api.message.ComplexConfigIdAndSeq;
import aut.bme.hu.configurator.api.message.ComplexConfigResponse;
import aut.bme.hu.configurator.api.message.Protocol;
import aut.bme.hu.configurator.core.config.ProtocolConfig;
import aut.bme.hu.configurator.core.dto.*;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapper;
import aut.bme.hu.configurator.core.model.ComplexConfig;
import aut.bme.hu.configurator.core.model.Config;
import aut.bme.hu.configurator.core.repository.ComplexConfigRepository;
import aut.bme.hu.configurator.core.repository.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConfiguratorService {

    private final ProtocolConfig protocolConfig;
    private final ConfigRepository configRepository;
    private final ComplexConfigRepository complexConfigRepository;
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

    public ComplexConfigResponse getComplexConfig(String complexId) {
        ComplexConfig complexConfig = complexConfigRepository
                .findById(complexId)
                .orElseThrow(EntityNotFoundException::new);

        complexConfig = getUpToDateComplexConfig(complexConfig);
        return getComplexConfigResponse(complexConfig);
    }

    public List<ComplexConfigResponse> getAllComplexConfigs() {
        List<ComplexConfig> complexConfigs = complexConfigRepository.findAll();
        complexConfigs = getUpToDateComplexConfigList(complexConfigs);

        List<ComplexConfigResponse> responseList = new ArrayList<>();
        for (ComplexConfig complexConfig : complexConfigs) {
            responseList.add(getComplexConfigResponse(complexConfig));
        }

        return responseList;
    }

    public void createComplexConfig(CreateComplexConfigMessage message) {
        List<String> complexConfigIdList = getConfigIdListFromComplexConfigIdAndSeq(message.getConfigList());

        ComplexConfig complexConfig = new ComplexConfig();
        complexConfig.setConfigIds(complexConfigIdList);
        complexConfigRepository.save(complexConfig);
    }

    public void updateComplexConfig(UpdateComplexConfigMessage message) {
        ComplexConfig complexConfigToUpdate = complexConfigRepository
                .findById(message.getComplexId())
                .orElseThrow(EntityNotFoundException::new);

        List<String> complexConfigIdList = getConfigIdListFromComplexConfigIdAndSeq(message.getConfigList());

        complexConfigToUpdate.setConfigIds(complexConfigIdList);
        complexConfigRepository.save(complexConfigToUpdate);
    }

    public void removeComplexConfig(String complexId) {
        complexConfigRepository.deleteById(complexId);
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

    private ComplexConfig getUpToDateComplexConfig(ComplexConfig complexConfig) {
        List<String> existingIds = new ArrayList<>();
        for (String id : complexConfig.getConfigIds()) {
            if (configRepository.findById(id).isPresent()) {
                existingIds.add(id);
            }
        }

        complexConfig.setConfigIds(existingIds);
        return complexConfigRepository.save(complexConfig);
    }

    private List<ComplexConfig> getUpToDateComplexConfigList(List<ComplexConfig> complexConfigList) {
        List<ComplexConfig> upToDateComplexConfigList = new ArrayList<>();
        for (ComplexConfig complexConfig : complexConfigList) {
            upToDateComplexConfigList.add(getUpToDateComplexConfig(complexConfig));
        }

        return upToDateComplexConfigList;
    }

    private ComplexConfigResponse getComplexConfigResponse(ComplexConfig complexConfig) {
        int sequenceNumber = 1;
        List<ComplexConfigIdAndSeq> complexConfigIdAndSeqList = new ArrayList<>();
        for (String id : complexConfig.getConfigIds()) {
            complexConfigIdAndSeqList.add(
                    ComplexConfigIdAndSeq.builder()
                            .id(id)
                            .sequenceNumber(sequenceNumber++)
                            .build());
        }

        return ComplexConfigResponse.builder()
                .id(complexConfig.getId())
                .configList(complexConfigIdAndSeqList)
                .build();
    }

    private List<String> getConfigIdListFromComplexConfigIdAndSeq(List<ComplexConfigIdAndSeq> idAndSeqList) {
        List<String> complexConfigIdList = new ArrayList<>();
        for (ComplexConfigIdAndSeq idAndSeq : idAndSeqList) {
            complexConfigIdList.add(idAndSeq.id);
        }
        return complexConfigIdList;
    }

}
