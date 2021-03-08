package aut.bme.hu.networkmonitor.core.service;

import aut.bme.hu.configurator.api.feign.ConfiguratorServiceFeignClient;
import aut.bme.hu.networkmonitor.api.message.NetworkmonitorResponse;
import aut.bme.hu.networkmonitor.core.dto.NetworkmonitorDTO;
import aut.bme.hu.networkmonitor.core.mapper.NetworkmonitorMapper;
import aut.bme.hu.networkmonitor.core.mapper.NetworkmonitorMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NetworkmonitorServiceTest {

    private static final String TEXT = "Test";
    private static final String WORLD = "World";

    private NetworkmonitorService service;

    @BeforeEach
    void init() {
        NetworkmonitorMapper networkmonitorMapper = new NetworkmonitorMapperImpl();
        ConfiguratorServiceFeignClient configuratorClient = mock(ConfiguratorServiceFeignClient.class);
        service = new NetworkmonitorService(networkmonitorMapper, configuratorClient);

        when(configuratorClient.getWorld())
                .thenReturn(WORLD);
    }

    @Test
    void testHelloWord() {
        NetworkmonitorDTO dto = createValidDto();

        NetworkmonitorResponse response = service.helloWorld(dto);

        assertEquals(response.text, TEXT + " Hello " + WORLD);
    }


    private NetworkmonitorDTO createValidDto() {
        return NetworkmonitorDTO.builder()
                .text(TEXT)
                .build();
    }

}
