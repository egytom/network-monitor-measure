package aut.bme.hu.networkmonitor.core.service;

import aut.bme.hu.configurator.api.feign.ConfiguratorServiceFeignClient;
import aut.bme.hu.networkmonitor.api.message.NetworkmonitorResponse;
import aut.bme.hu.networkmonitor.core.dto.NetworkmonitorDTO;
import aut.bme.hu.networkmonitor.core.mapper.NetworkmonitorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NetworkmonitorService {

    private final NetworkmonitorMapper networkmonitorMapper;
    private final ConfiguratorServiceFeignClient configuratorClient;

    public NetworkmonitorResponse helloWorld(NetworkmonitorDTO dto) {
        dto.text = dto.text + " Hello " + configuratorClient.getWorld();
        return networkmonitorMapper.toResponse(dto);
    }
    
}
