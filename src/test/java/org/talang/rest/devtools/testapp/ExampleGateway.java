package org.talang.rest.devtools.testapp;

import org.springframework.stereotype.Component;
import org.talang.rest.devtools.gateway.GatewayCommon;

@Component
public class ExampleGateway extends GatewayCommon {

    public static final String GATEWAY_ENDPOINT = "http://localhost:8080/examplegateway";

    public ExampleDTO getExampleDTO() {
        return genericGet(GATEWAY_ENDPOINT, ExampleDTO.class);
    }

    public void putExampleDTO(ExampleDTO exampleDTO) {
        genericPut(GATEWAY_ENDPOINT,exampleDTO);
    }
}
