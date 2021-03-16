package aut.bme.hu.configurator.api.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateConfigRequest {

    @NotNull
    @Size(max = 100)
    public String name;

    @NotNull
    public Protocol protocol;

    @NotNull
    public Category category;

    @NotNull
    @Max(value = 300)
    public int durationInSec;

}
