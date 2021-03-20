package aut.bme.hu.configurator.core.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetConfigsByIdsMessage {

    private List<String> ids;

}
