package aut.bme.hu.configurator.core.service;

import aut.bme.hu.configurator.api.message.Category;
import aut.bme.hu.configurator.api.message.Protocol;
import aut.bme.hu.configurator.core.TestBase;
import aut.bme.hu.configurator.core.config.ProtocolConfig;
import aut.bme.hu.configurator.core.dto.ComplexConfigResult;
import aut.bme.hu.configurator.core.dto.GetConfigsByIdsMessage;
import aut.bme.hu.configurator.core.dto.UpdateConfigMessage;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapper;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapperImpl;
import aut.bme.hu.configurator.core.model.Config;
import aut.bme.hu.configurator.core.repository.ConfigRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConfiguratorServiceTest extends TestBase {

    private ConfiguratorService service;
    private ConfigRepository repository;

    @BeforeEach
    void init() {
        ProtocolConfig protocolConfig = mock(ProtocolConfig.class);
        repository = mock(ConfigRepository.class);
        ConfiguratorMapper configuratorMapper = new ConfiguratorMapperImpl();
        service = new ConfiguratorService(protocolConfig, repository, configuratorMapper);
    }

    @Test
    void getConfig_EntityFound() {
        Config config = createConfig("1");
        when(repository.findById(anyString())).thenReturn(Optional.of(config));

        Config result = service.getConfig("id");

        assertConfig(result, config);
    }

    @Test
    void getConfig_EntityNotFound() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getConfig("id"));
    }

    @Test
    void updateConfig_EntityNotFound() {
        UpdateConfigMessage message = new UpdateConfigMessage();
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateConfig(message));
    }

    @Test
    void getConfigsByIds() {
        Config config1 = createConfig("1");
        Config config2 = createConfig("2");
        List<Config> configList = List.of(config1, config2);
        when(repository.findAllById(anyIterable())).thenReturn(configList);
        GetConfigsByIdsMessage message = GetConfigsByIdsMessage.builder().ids(List.of("1", "2")).build();

        List<ComplexConfigResult> resultList = service.getConfigsByIds(message);

        assertComplexResult(resultList, configList);
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

}
