package aut.bme.hu.configurator.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("aut.bme.hu")
public class ConfiguratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfiguratorApplication.class, args);
    }

}
