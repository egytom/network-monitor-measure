package aut.bme.hu.configurator.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "complex_config",
        indexes = {
                @Index(name = "complex_config_id_idx", columnList = "id", unique = true)
        }
)
public class ComplexConfig {

    @Id
    private String id = UUID.randomUUID().toString();

    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> configIds = new ArrayList<>();

}
