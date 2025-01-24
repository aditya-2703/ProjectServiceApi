package com.ideabazaar.project.projectserviceapi.client;

import com.ideabazaar.project.projectserviceapi.dto.ResourceDTO;
import feign.Feign;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.stereotype.Component;
import feign.slf4j.Slf4jLogger;


import java.util.List;


//@FeignClient(name = "projectserv  ice", url = "${projectServiceConfig.url}")
@Component
public interface ResourceClient {

    @RequestLine("GET /customresources/{id}")
    List<ResourceDTO> getSingleResource(@Param("id") Long id);

    // Static factory method to create the Feign client instance
    static ResourceClient create() {
        return Feign.builder()
                .decoder(new GsonDecoder()) // Jackson decoder
                .encoder(new GsonEncoder()) // Jackson encoder (if you need POST/PUT requests)
                .logger(new Slf4jLogger(ResourceClient.class))
                .logLevel(Logger.Level.FULL)
                .target(ResourceClient.class, "http://localhost:8081/api"); // Replace with your base URL
    }

}



