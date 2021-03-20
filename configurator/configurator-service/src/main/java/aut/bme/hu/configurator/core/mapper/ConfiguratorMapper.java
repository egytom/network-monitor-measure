package aut.bme.hu.configurator.core.mapper;

import aut.bme.hu.configurator.api.message.*;
import aut.bme.hu.configurator.core.dto.ComplexConfigResult;
import aut.bme.hu.configurator.core.dto.CreateConfigMessage;
import aut.bme.hu.configurator.core.dto.GetConfigsByIdsMessage;
import aut.bme.hu.configurator.core.dto.UpdateConfigMessage;
import aut.bme.hu.configurator.core.model.Config;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfiguratorMapper {

    ConfigResponse toResponse(Config config);

    ComplexConfigResponse toResponse(ComplexConfigResult result);

    CreateConfigMessage toMessage(CreateConfigRequest request);

    UpdateConfigMessage toMessage(UpdateConfigRequest request);

    GetConfigsByIdsMessage toMessage(GetConfigsByIdsRequest request);

    Config toConfig(CreateConfigMessage message);

    ComplexConfigResult toResult(Config config);

}
