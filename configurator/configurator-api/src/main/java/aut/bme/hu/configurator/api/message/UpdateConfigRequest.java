package aut.bme.hu.configurator.api.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateConfigRequest {

    @NotEmpty(message = "UUID can not be empty during updating!")
    public String id;

    @Size(max = 100)
    public String name;

    public Protocol protocol;

    public Category category;

    @Max(value = 300)
    public int durationInSec;

}
