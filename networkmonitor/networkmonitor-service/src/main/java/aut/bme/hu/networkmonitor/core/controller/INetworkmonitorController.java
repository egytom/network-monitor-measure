package aut.bme.hu.networkmonitor.core.controller;

import aut.bme.hu.networkmonitor.api.message.NetworkmonitorRequest;
import aut.bme.hu.networkmonitor.api.message.NetworkmonitorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public interface INetworkmonitorController {

    @PostMapping("/hello")
    NetworkmonitorResponse helloWorld(@Valid @RequestBody NetworkmonitorRequest request);

}
