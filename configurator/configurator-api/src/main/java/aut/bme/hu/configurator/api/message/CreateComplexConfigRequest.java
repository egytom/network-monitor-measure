package aut.bme.hu.configurator.api.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateComplexConfigRequest {

    @NotNull
    @Size(max = 100)
    public String name;

    @NotEmpty
    public List<ComplexConfigIdAndSeq> configList;

}
