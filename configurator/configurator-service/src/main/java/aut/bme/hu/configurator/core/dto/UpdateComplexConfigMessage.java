package aut.bme.hu.configurator.core.dto;

import aut.bme.hu.configurator.api.message.ComplexConfigIdAndSeq;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateComplexConfigMessage {

    private String complexId;
    private List<ComplexConfigIdAndSeq> configList;

}
