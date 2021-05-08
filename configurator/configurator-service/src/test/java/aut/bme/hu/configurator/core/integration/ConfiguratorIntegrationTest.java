package aut.bme.hu.configurator.core.integration;

import aut.bme.hu.configurator.api.message.*;
import aut.bme.hu.configurator.core.TestBase;
import aut.bme.hu.configurator.core.config.ProtocolConfig;
import aut.bme.hu.configurator.core.controller.ConfiguratorController;
import aut.bme.hu.configurator.core.dto.ComplexConfigResult;
import aut.bme.hu.configurator.core.dto.UpdateConfigMessage;
import aut.bme.hu.configurator.core.mapper.ConfiguratorMapper;
import aut.bme.hu.configurator.core.model.ComplexConfig;
import aut.bme.hu.configurator.core.model.Config;
import aut.bme.hu.configurator.core.repository.ComplexConfigRepository;
import aut.bme.hu.configurator.core.repository.ConfigRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ConfiguratorIntegrationTest extends TestBase {

    @MockBean
    private ProtocolConfig protocolConfig;

    @Autowired
    private ConfiguratorController controller;

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private ComplexConfigRepository complexConfigRepository;

    @Autowired
    private ConfiguratorMapper mapper;

    @Test
    void getConfig() {
        Config config = saveConfig();

        ConfigResponse response = controller.getConfig(config.getId());

        assertConfig(toConfig(response), config);
    }

    @Test
    void createConfig() {
        CreateConfigRequest request = createCreateConfigRequest();

        controller.createConfig(request);

        List<ConfigResponse> responseList = controller.getAllConfigs();

        List<Config> resultList = responseList.stream().map(this::toConfig).collect(Collectors.toList());
        Assertions.assertNotNull(resultList);
        Config config = mapper.toConfig(mapper.toMessage(request));
        config.setId(resultList.get(0).getId());
        assertConfig(resultList.get(0), config);
    }

    @Test
    void removeConfig() {
        Config config = saveConfig();

        controller.removeConfig(config.getId());

        assertThrows(ResponseStatusException.class, () -> controller.getConfig(config.getId()));
    }

    @Test
    void getAllConfigs() {
        Config config1 = saveConfig();
        Config config2 = saveConfig();
        Config config3 = saveConfig();
        List<Config> configList = List.of(config1, config2, config3);

        List<ConfigResponse> responseList = controller.getAllConfigs();

        List<Config> resultList = responseList.stream().map(this::toConfig).collect(Collectors.toList());
        for (int i = 0; i < resultList.size() && i < configList.size(); i++) {
            assertConfig(resultList.get(i), configList.get(i));
        }
    }

    @Test
    void updateConfig() {
        Config config = saveConfig();
        UpdateConfigRequest request = createUpdateConfigRequest(config.getId());

        controller.updateConfig(request);
        ConfigResponse response = controller.getConfig(config.getId());

        assertConfigAfterUpdate(toConfig(response), mapper.toMessage(request));
    }

    @Test
    void getConfigsByIds() {
        Config config1 = saveConfig();
        Config config2 = saveConfig();
        Config configNotNeeded = saveConfig();
        List<Config> configList = List.of(config1, config2);
        List<String> ids = List.of(config1.getId(), config2.getId());
        GetConfigsByIdsRequest request = GetConfigsByIdsRequest.builder().ids(ids).build();

        List<ComplexConfigElement> responseList = controller.getConfigsByIds(request);

        List<ComplexConfigResult> resultList = responseList.stream().map(this::toResult).collect(Collectors.toList());
        assertComplexResult(resultList, configList);
        Assertions.assertTrue(resultList.stream().filter(r -> !ids.contains(r.getId())).findAny().isEmpty());
    }

    @Test
    void getComplexConfig() {
        Config config = saveConfig();
        ComplexConfig complexConfig = saveComplexConfig(List.of(config.getId()));

        ComplexConfigResponse result = controller.getComplexConfig(complexConfig.getId());

        assertComplexConfig(result, complexConfig);
    }

    @Test
    void createComplexConfig() {
        Config config = saveConfig();
        CreateComplexConfigRequest request = createCreateComplexConfigRequest(List.of(config.getId()));

        controller.createComplexConfig(request);

        List<ComplexConfigResponse> resultList = controller.getAllComplexConfigs();
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(request.configList.size(), resultList.get(0).configList.size());
        Assertions.assertEquals(request.configList.get(0).id, resultList.get(0).configList.get(0).id);
        Assertions.assertEquals(request.configList.get(0).sequenceNumber, resultList.get(0).configList.get(0).sequenceNumber);
    }

    @Test
    void removeComplexConfig() {
        ComplexConfig complexConfig = saveComplexConfig(List.of());

        controller.removeComplexConfig(complexConfig.getId());

        assertThrows(ResponseStatusException.class, () -> controller.getComplexConfig(complexConfig.getId()));
    }

    @Test
    void updateComplexConfig() {
        Config config = saveConfig();
        ComplexConfig complexConfig = saveComplexConfig(List.of(config.getId(), config.getId()));
        UpdateComplexConfigRequest request = createUpdateComplexConfigRequest(complexConfig.getId(), List.of(config.getId()));

        controller.updateComplexConfig(request);

        List<ComplexConfigResponse> resultList = controller.getAllComplexConfigs();
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(request.complexId, resultList.get(0).id);
        Assertions.assertEquals(request.configList.size(), resultList.get(0).configList.size());
        Assertions.assertEquals(request.configList.get(0).id, resultList.get(0).configList.get(0).id);
        Assertions.assertEquals(request.configList.get(0).sequenceNumber, resultList.get(0).configList.get(0).sequenceNumber);
    }

    private Config saveConfig() {
        Config config = new Config();
        config.setName("name");
        config.setCategory(Category.DEFAULT);
        config.setProtocol(Protocol.HTTP);
        config.setDurationInSec(10);
        return configRepository.save(config);
    }

    private CreateConfigRequest createCreateConfigRequest() {
        return CreateConfigRequest.builder()
                .name("name")
                .category(Category.DEFAULT)
                .protocol(Protocol.HTTP)
                .durationInSec(10)
                .build();
    }

    private UpdateConfigRequest createUpdateConfigRequest(String id) {
        return UpdateConfigRequest.builder()
                .id(id)
                .name("updated_name")
                .category(Category.DEFAULT)
                .protocol(Protocol.TCP)
                .durationInSec(100)
                .build();
    }

    private ComplexConfig saveComplexConfig(List<String> ids) {
        ComplexConfig complexConfig = new ComplexConfig();
        complexConfig.setName("name");
        complexConfig.setConfigIds(ids);
        return complexConfigRepository.save(complexConfig);
    }

    private CreateComplexConfigRequest createCreateComplexConfigRequest(List<String> configIds) {
        List<ComplexConfigIdAndSeq> complexConfigIdAndSeqList = getComplexConfigIdAndSeqList(configIds);

        return CreateComplexConfigRequest.builder()
                .name("name")
                .configList(complexConfigIdAndSeqList)
                .build();
    }

    private UpdateComplexConfigRequest createUpdateComplexConfigRequest(String id, List<String> configIds) {
        List<ComplexConfigIdAndSeq> complexConfigIdAndSeqList = getComplexConfigIdAndSeqList(configIds);

        return UpdateComplexConfigRequest.builder()
                .complexId(id)
                .name("name")
                .configList(complexConfigIdAndSeqList)
                .build();
    }

    private List<ComplexConfigIdAndSeq> getComplexConfigIdAndSeqList(List<String> configIds) {
        List<ComplexConfigIdAndSeq> complexConfigIdAndSeqList = new ArrayList<>();
        for (int i = 1; i <= configIds.size(); i++) {
            complexConfigIdAndSeqList.add(new ComplexConfigIdAndSeq(configIds.get(i - 1), i));
        }
        return complexConfigIdAndSeqList;
    }

    private void assertConfigAfterUpdate(Config result, UpdateConfigMessage message) {
        Assertions.assertEquals(result.getId(), message.getId());
        Assertions.assertEquals(result.getName(), message.getName());
        Assertions.assertEquals(result.getCategory(), message.getCategory());
        Assertions.assertEquals(result.getDurationInSec(), message.getDurationInSec());
        Assertions.assertEquals(result.getProtocol(), message.getProtocol());
    }

    private Config toConfig(ConfigResponse configResponse) {
        if (configResponse == null) {
            return null;
        }

        Config.ConfigBuilder configBuilder = Config.builder();

        configBuilder.id(configResponse.id);
        configBuilder.name(configResponse.name);
        configBuilder.protocol(configResponse.protocol);
        configBuilder.category(configResponse.category);
        configBuilder.durationInSec(configResponse.durationInSec);

        return configBuilder.build();
    }

    private ComplexConfigResult toResult(ComplexConfigElement element) {
        if (element == null) {
            return null;
        }

        ComplexConfigResult.ComplexConfigResultBuilder complexConfigResult = ComplexConfigResult.builder();

        complexConfigResult.id(element.id);
        complexConfigResult.name(element.name);
        complexConfigResult.protocol(element.protocol);
        complexConfigResult.category(element.category);
        complexConfigResult.durationInSec(element.durationInSec);
        complexConfigResult.sequenceNumber(element.sequenceNumber);

        return complexConfigResult.build();
    }

}
