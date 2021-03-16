package aut.bme.hu.configurator.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "configurator.protocol")
public class ProtocolConfig {

    private String defaultType;

}
