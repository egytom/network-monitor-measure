package aut.bme.hu.configurator.api.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateComplexConfigRequest {

    @NotEmpty
    public List<ComplexConfigIdAndSeq> configList;

}
