package aut.bme.hu.configurator.core.dto;

import aut.bme.hu.configurator.api.message.ComplexConfigIdAndSeq;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateComplexConfigMessage {

    private String name;
    private List<ComplexConfigIdAndSeq> configList;

}
