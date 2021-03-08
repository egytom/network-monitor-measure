package aut.bme.hu.networkmonitor.core.mapper;

import aut.bme.hu.networkmonitor.api.message.NetworkmonitorRequest;
import aut.bme.hu.networkmonitor.api.message.NetworkmonitorResponse;
import aut.bme.hu.networkmonitor.core.dto.NetworkmonitorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NetworkmonitorMapper {

    NetworkmonitorDTO toDTO(NetworkmonitorRequest request);

    NetworkmonitorResponse toResponse(NetworkmonitorDTO dto);

}
