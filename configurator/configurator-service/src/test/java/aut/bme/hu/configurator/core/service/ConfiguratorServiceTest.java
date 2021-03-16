package aut.bme.hu.configurator.core.service;

import aut.bme.hu.configurator.core.config.ProtocolConfig;
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
        service = new ConfiguratorService(protocolConfig, configRepository);
    }

    @Test
    void testGetWorld() {
        assertTrue(true);
    }

}
