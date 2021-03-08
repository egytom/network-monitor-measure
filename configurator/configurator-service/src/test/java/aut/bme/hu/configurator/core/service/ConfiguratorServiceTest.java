package aut.bme.hu.configurator.core.service;

import aut.bme.hu.configurator.core.config.DictionaryConfig;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapper;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConfiguratorServiceTest {

    private static final String WORLD = "World";

    private ConfiguratorService service;

    @BeforeEach
    void init() {
        ConfiguratorMapper configuratorMapper = new ConfiguratorMapperImpl();
        DictionaryConfig dictionaryConfig = mock(DictionaryConfig.class);
        service = new ConfiguratorService(configuratorMapper, dictionaryConfig);

        when(dictionaryConfig.getText()).thenReturn(WORLD);
    }

    @Test
    void testGetWorld() {
        String response = service.getWorld();

        assertEquals(WORLD, response);
    }

}
