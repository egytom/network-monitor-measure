package aut.bme.hu.networkmonitor.api.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NetworkmonitorRequest {

    @NotNull
    @Size(max = 50)
    public String text;

}
