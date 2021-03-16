package aut.bme.hu.configurator.api.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigResponse {

    public String id;
    public String name;
    public Protocol protocol;
    public Category category;
    public int durationInSec;

}
