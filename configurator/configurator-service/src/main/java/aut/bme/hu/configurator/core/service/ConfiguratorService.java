package aut.bme.hu.configurator.core.service;

import aut.bme.hu.configurator.core.config.DictionaryConfig;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfiguratorService {

    private final ConfiguratorMapper configuratorMapper;
    private final DictionaryConfig dictionaryConfig;

    public String getWorld() {
        return dictionaryConfig.getText();
    }

}
