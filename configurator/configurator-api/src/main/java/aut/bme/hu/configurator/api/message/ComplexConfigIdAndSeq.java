package aut.bme.hu.configurator.api.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplexConfigIdAndSeq {

    public String id;
    public int sequenceNumber;

}
