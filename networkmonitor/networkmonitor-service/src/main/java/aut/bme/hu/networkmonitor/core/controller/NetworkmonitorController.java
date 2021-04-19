package aut.bme.hu.networkmonitor.core.controller;

import aut.bme.hu.networkmonitor.api.message.NetworkmonitorRequest;
import aut.bme.hu.networkmonitor.api.message.NetworkmonitorResponse;
import aut.bme.hu.networkmonitor.core.mapper.NetworkmonitorMapper;
import aut.bme.hu.networkmonitor.core.service.NetworkmonitorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class NetworkmonitorController implements INetworkmonitorController {

    private final NetworkmonitorService networkmonitorService;
    private final NetworkmonitorMapper networkmonitorMapper;

    @Override
    public NetworkmonitorResponse helloWorld(@Valid @RequestBody NetworkmonitorRequest request) {
        log.debug("-----> HelloWorld request through /networkmonitor/hello");
        return networkmonitorService.helloWorld(networkmonitorMapper.toDTO(request));
    }

}
