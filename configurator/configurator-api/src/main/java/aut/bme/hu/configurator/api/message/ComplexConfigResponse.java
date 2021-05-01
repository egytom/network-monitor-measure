package aut.bme.hu.configurator.api.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplexConfigResponse {

    public String id;
    public String name;
    public List<ComplexConfigIdAndSeq> configList;

}
