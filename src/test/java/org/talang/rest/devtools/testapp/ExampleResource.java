package org.talang.rest.devtools.testapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.talang.rest.devtools.gateway.GatewayException;
import org.talang.rest.devtools.web.CommonRestErrors;
import org.talang.rest.devtools.web.RestException;
import org.talang.rest.devtools.web.util.RestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/example")
public class ExampleResource {

    @Autowired
    ExampleGateway gateway;


    @RequestMapping(value = "/stringlist",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<String> getStringList() {
        return Arrays.asList(new String[]{"a","b"});
    }

    @RequestMapping(value = "/stringlist",
            method = RequestMethod.POST,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<String> takeStringList(@RequestBody List<String> strings) {
        return strings;
    }

    @RequestMapping(value = "/exception/npe",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<String> throwNpe() {
        throw new NullPointerException("Null pointer ex!");
    }


    @RequestMapping(value = "/exception/restexception",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<String> throwRestException() {
        throw new RestException(CommonRestErrors.GENERAL_SERVICE_ERROR.toRestError(),null);
    }

    @RequestMapping(value = "/exception/gatewayexception",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<String> throwGatewayException() {
        throw new GatewayException(RestUtils.createErrorDTOFromRestError(CommonRestErrors.GENERAL_UPDATE_CODE_CONFLICT.toRestError()),CommonRestErrors.GENERAL_UPDATE_CODE_CONFLICT.getHttpStatus());
    }

    @RequestMapping(value = "/gateway/getexample",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ExampleDTO getExample() {
        return gateway.getExampleDTO();
    }


    @RequestMapping(value = "/gateway/putexample",
            method = RequestMethod.PUT,
            consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void putExample(@RequestBody ExampleDTO exampleDTO) {
         gateway.putExampleDTO(exampleDTO);
    }
}
