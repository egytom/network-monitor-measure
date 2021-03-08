package aut.bme.hu.networkmonitor.api.feign;

import aut.bme.hu.networkmonitor.api.message.NetworkmonitorRequest;
import aut.bme.hu.networkmonitor.api.message.NetworkmonitorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("networkmonitor-service")
public interface NetworkmonitorServiceFeignClient {

    @PostMapping("/networkmonitor/hello")
    NetworkmonitorResponse helloWorld(@RequestBody NetworkmonitorRequest request);

}
