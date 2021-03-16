package aut.bme.hu.configurator.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
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

    // TODO - add missing fields

}
