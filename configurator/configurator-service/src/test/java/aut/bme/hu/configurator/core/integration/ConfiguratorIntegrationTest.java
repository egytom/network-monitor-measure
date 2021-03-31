package aut.bme.hu.configurator.core.integration;

import aut.bme.hu.configurator.api.message.Category;
import aut.bme.hu.configurator.api.message.Protocol;
import aut.bme.hu.configurator.core.TestBase;
import aut.bme.hu.configurator.core.config.ProtocolConfig;
import aut.bme.hu.configurator.core.dto.ComplexConfigResult;
import aut.bme.hu.configurator.core.dto.CreateConfigMessage;
import aut.bme.hu.configurator.core.dto.GetConfigsByIdsMessage;
import aut.bme.hu.configurator.core.dto.UpdateConfigMessage;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapper;
import aut.bme.hu.configurator.core.model.Config;
import aut.bme.hu.configurator.core.repository.ConfigRepository;
import aut.bme.hu.configurator.core.service.ConfiguratorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ConfiguratorIntegrationTest extends TestBase {

    @MockBean
    private ProtocolConfig protocolConfig;

    @Autowired
    private ConfiguratorService service;

    @Autowired
    private ConfigRepository repository;

    @Autowired
    private ConfiguratorMapper mapper;

    @Test
    void getConfig() {
        Config config = saveConfig();

        Config result = service.getConfig(config.getId());

        assertConfig(result, config);
    }

    @Test
    void createConfig() {
        CreateConfigMessage message = createCreateConfigMessage();

        service.createConfig(message);

        List<Config> resultList = service.getAllConfigs();
        assertNotNull(resultList);
        Config config = mapper.toConfig(message);
        config.setId(resultList.get(0).getId());
        assertConfig(resultList.get(0), config);
    }

    @Test
    void removeConfig() {
        Config config = saveConfig();

        service.removeConfig(config.getId());

        assertThrows(EntityNotFoundException.class, () -> service.getConfig(config.getId()));
    }

    @Test
    void getAllConfigs() {
        Config config1 = saveConfig();
        Config config2 = saveConfig();
        Config config3 = saveConfig();
        List<Config> configList = List.of(config1, config2, config3);

        List<Config> resultList = service.getAllConfigs();

        for (int i = 0; i < resultList.size() && i < configList.size(); i++) {
            assertConfig(resultList.get(i), configList.get(i));
        }
    }

    @Test
    void updateConfig() {
        Config config = saveConfig();
        UpdateConfigMessage message = createUpdateConfigMessage(config.getId());

        service.updateConfig(message);
        Config result = service.getConfig(config.getId());

        assertConfigAfterUpdate(result, message);
    }

    @Test
    void getConfigsByIds() {
        Config config1 = saveConfig();
        Config config2 = saveConfig();
        Config configNotNeeded = saveConfig();
        List<Config> configList = List.of(config1, config2);
        List<String> ids = List.of(config1.getId(), config2.getId());
        GetConfigsByIdsMessage message = GetConfigsByIdsMessage.builder().ids(ids).build();

        List<ComplexConfigResult> resultList = service.getConfigsByIds(message);

        assertComplexResult(resultList, configList);
        assertTrue(resultList.stream().filter(r -> !ids.contains(r.getId())).findAny().isEmpty());
    }

    private Config saveConfig() {
        Config config = new Config();
        config.setName("name");
        config.setCategory(Category.DEFAULT);
        config.setProtocol(Protocol.HTTP);
        config.setDurationInSec(10);
        return repository.save(config);
    }

    private CreateConfigMessage createCreateConfigMessage() {
        return CreateConfigMessage.builder()
                .name("name")
                .category(Category.DEFAULT)
                .protocol(Protocol.HTTP)
                .durationInSec(10)
                .build();
    }

    private UpdateConfigMessage createUpdateConfigMessage(String id) {
        return UpdateConfigMessage.builder()
                .id(id)
                .name("updated_name")
                .category(Category.DEFAULT)
                .protocol(Protocol.TCP)
                .durationInSec(100)
                .build();
    }

    private void assertConfigAfterUpdate(Config result, UpdateConfigMessage message) {
        assertEquals(result.getId(), message.getId());
        assertEquals(result.getName(), message.getName());
        assertEquals(result.getCategory(), message.getCategory());
        assertEquals(result.getDurationInSec(), message.getDurationInSec());
        assertEquals(result.getProtocol(), message.getProtocol());
    }

}
