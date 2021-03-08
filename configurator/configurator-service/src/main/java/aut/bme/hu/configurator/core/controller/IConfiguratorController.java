package aut.bme.hu.configurator.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface IConfiguratorController {

    @GetMapping("/world")
    String getWorld();

}
