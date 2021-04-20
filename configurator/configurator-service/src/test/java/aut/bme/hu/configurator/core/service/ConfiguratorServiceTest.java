package aut.bme.hu.configurator.core.service;

import aut.bme.hu.configurator.api.message.Category;
import aut.bme.hu.configurator.api.message.ComplexConfigResponse;
import aut.bme.hu.configurator.api.message.Protocol;
import aut.bme.hu.configurator.core.TestBase;
import aut.bme.hu.configurator.core.config.ProtocolConfig;
import aut.bme.hu.configurator.core.dto.ComplexConfigResult;
import aut.bme.hu.configurator.core.dto.GetConfigsByIdsMessage;
import aut.bme.hu.configurator.core.dto.UpdateComplexConfigMessage;
import aut.bme.hu.configurator.core.dto.UpdateConfigMessage;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapper;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapperImpl;
import aut.bme.hu.configurator.core.model.ComplexConfig;
import aut.bme.hu.configurator.core.model.Config;
import aut.bme.hu.configurator.core.repository.ComplexConfigRepository;
import aut.bme.hu.configurator.core.repository.ConfigRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConfiguratorServiceTest extends TestBase {

    private ConfiguratorService service;
    private ConfigRepository configRepository;
    private ComplexConfigRepository complexConfigRepository;

    @BeforeEach
    void init() {
        ProtocolConfig protocolConfig = mock(ProtocolConfig.class);
        configRepository = mock(ConfigRepository.class);
        complexConfigRepository = mock(ComplexConfigRepository.class);
        ConfiguratorMapper configuratorMapper = new ConfiguratorMapperImpl();
        service = new ConfiguratorService(protocolConfig, configRepository, complexConfigRepository, configuratorMapper);
    }

    @Test
    void getConfig_EntityFound() {
        Config config = createConfig("1");
        when(configRepository.findById(anyString())).thenReturn(Optional.of(config));

        Config result = service.getConfig("id");

        assertConfig(result, config);
    }

    @Test
    void getConfig_EntityNotFound() {
        when(configRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getConfig("id"));
    }

    @Test
    void updateConfig_EntityNotFound() {
        UpdateConfigMessage message = new UpdateConfigMessage();
        when(configRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateConfig(message));
    }

    @Test
    void getConfigsByIds() {
        Config config1 = createConfig("1");
        Config config2 = createConfig("2");
        List<Config> configList = List.of(config1, config2);
        when(configRepository.findAllById(anyIterable())).thenReturn(configList);
        GetConfigsByIdsMessage message = GetConfigsByIdsMessage.builder().ids(List.of("1", "2")).build();

        List<ComplexConfigResult> resultList = service.getConfigsByIds(message);

        assertComplexResult(resultList, configList);
    }

    @Test
    void getComplexConfig_EntityFound() {
        ComplexConfig complexConfig = createComplexConfig("id", List.of("1"));
        when(complexConfigRepository.findById(anyString())).thenReturn(Optional.of(complexConfig));
        when(complexConfigRepository.save(any())).thenReturn(complexConfig);

        ComplexConfigResponse result = service.getComplexConfig("id");

        assertComplexConfig(result, complexConfig);
    }

    @Test
    void getComplexConfig_EntityNotFound() {
        when(complexConfigRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getComplexConfig("id"));
    }

    @Test
    void updateComplexConfig_EntityNotFound() {
        UpdateComplexConfigMessage message = new UpdateComplexConfigMessage();
        when(complexConfigRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateComplexConfig(message));
    }

    private Config createConfig(String id) {
        return Config.builder()
                .id(id)
                .name("name")
                .category(Category.DEFAULT)
                .protocol(Protocol.HTTP)
                .durationInSec(10)
                .build();
    }

    private ComplexConfig createComplexConfig(String id, List<String> ids) {
        return ComplexConfig.builder()
                .id(id)
                .configIds(ids)
                .build();
    }

}
