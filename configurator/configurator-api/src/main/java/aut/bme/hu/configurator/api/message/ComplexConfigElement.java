package aut.bme.hu.configurator.api.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplexConfigElement {

    public String id;
    public String name;
    public Protocol protocol;
    public Category category;
    public int durationInSec;
    public int sequenceNumber;

}
