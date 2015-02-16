package org.talang.rest.devtools.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.talang.rest.devtools.web.ErrorDTO;
import org.talang.rest.devtools.web.CommonRestErrors;
import org.talang.rest.devtools.web.util.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class GatewayExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayExceptionHandler.class);

    public static void handleRestClientException(RestClientException ex, String endpointUrl, Object payload){
        if(ex instanceof HttpStatusCodeException){
            handleHttpStatusCodeException((HttpStatusCodeException) ex,endpointUrl, payload);
        } else {
            LOGGER.error("Handle RestClientException on endpoint {}", endpointUrl, ex);
            Map<String, Object> errorParams = new HashMap<>();
            errorParams.put("errorDetails",ex.getMessage());
            throw new GatewayException(RestUtils.createErrorDTOFromRestError(CommonRestErrors.GENERAL_SERVICE_ERROR.toRestError(), errorParams), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    protected static ErrorDTO getErrorFromHttpStatusCodeException(HttpStatusCodeException e) {
        ErrorDTO error = null;
        try {
            error = new ObjectMapper().readValue(e.getResponseBodyAsByteArray(), ErrorDTO.class);
        } catch (Exception ex) {
            LOGGER.error("Could not deserialize ErrorDTO", ex);
        }
        return error;
    }

    protected static void handleHttpStatusCodeException(HttpStatusCodeException ex, String endpointUrl, Object payload){
        LOGGER.error("Handle HttpStatusCodeException {} on endpoint {} {}", ex.getStatusCode(), endpointUrl, payload);
        throw new GatewayException(getErrorFromHttpStatusCodeException(ex),ex.getStatusCode());
    }

}
