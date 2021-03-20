package aut.bme.hu.configurator.core.dto;

import aut.bme.hu.configurator.api.message.Category;
import aut.bme.hu.configurator.api.message.Protocol;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplexConfigResult {

    private String id;
    private String name;
    private Protocol protocol;
    private Category category;
    private int durationInSec;
    private int sequenceNumber;

}
