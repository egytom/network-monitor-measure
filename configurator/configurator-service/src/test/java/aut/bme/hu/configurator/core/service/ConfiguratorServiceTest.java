package aut.bme.hu.configurator.core.service;

import aut.bme.hu.configurator.core.config.ProtocolConfig;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapper;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapperImpl;
import aut.bme.hu.configurator.core.repository.ConfigRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

class ConfiguratorServiceTest {

    private ConfiguratorService service;

    @BeforeEach
    void init() {
        ProtocolConfig protocolConfig = mock(ProtocolConfig.class);
        ConfigRepository configRepository = mock(ConfigRepository.class);
        ConfiguratorMapper configuratorMapper = new ConfiguratorMapperImpl();
        service = new ConfiguratorService(protocolConfig, configRepository, configuratorMapper);
    }

    @Test
    void testGetWorld() {
        assertTrue(true);
    }

}
