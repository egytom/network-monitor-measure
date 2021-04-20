package aut.bme.hu.configurator.core.mapper;

import aut.bme.hu.configurator.api.message.*;
import aut.bme.hu.configurator.core.dto.*;
import aut.bme.hu.configurator.core.model.Config;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfiguratorMapper {

    ConfigResponse toResponse(Config config);

    ComplexConfigElement toResponse(ComplexConfigResult result);

    CreateConfigMessage toMessage(CreateConfigRequest request);

    UpdateConfigMessage toMessage(UpdateConfigRequest request);

    GetConfigsByIdsMessage toMessage(GetConfigsByIdsRequest request);

    CreateComplexConfigMessage toMessage(CreateComplexConfigRequest request);

    UpdateComplexConfigMessage toMessage(UpdateComplexConfigRequest request);

    Config toConfig(CreateConfigMessage message);

    ComplexConfigResult toResult(Config config);

}
