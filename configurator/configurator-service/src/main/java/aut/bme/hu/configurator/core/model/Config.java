package aut.bme.hu.configurator.core.model;

import aut.bme.hu.configurator.api.message.Category;
import aut.bme.hu.configurator.api.message.Protocol;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "config",
        indexes = {
                @Index(name = "config_id_idx", columnList = "id", unique = true)
        }
)
public class Config {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;

    @Enumerated(EnumType.STRING)
    private Protocol protocol;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int durationInSec;

}
