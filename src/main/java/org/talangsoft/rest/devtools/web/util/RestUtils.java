package org.talangsoft.rest.devtools.web.util;

import org.talangsoft.rest.devtools.web.ErrorDTO;
import org.talangsoft.rest.devtools.web.RestError;
import org.talangsoft.rest.devtools.web.TranslatableToRestError;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by tamaslang on 26/11/14.
 */
public class RestUtils {

    public static final String EXCEPTION_MESSAGE_KEY = "exceptionMessage";

    private RestUtils() {
        // utility class
    }

    public static ErrorDTO createErrorDTOFromRestError(RestError restError){
        return createErrorDTOFromRestError(restError,null);
    }

    public static  ErrorDTO createErrorDTOFromRestError(RestError restError, Map<String,Object> params){
        return params != null? new ErrorDTO(restError,params) : new ErrorDTO(restError);
    }

    public static  ErrorDTO createErrorDTOFromRestError(RestError restError, Map<String,Object> params, Throwable ex){
        ErrorDTO errorDTO = createErrorDTOFromRestError(restError,params);
        errorDTO.getParams().put(EXCEPTION_MESSAGE_KEY,ex.getMessage());
        return errorDTO;
    }

    public static ResponseEntity<ErrorDTO> createErrorResponseEntity(TranslatableToRestError restErrorSource, Map<String,Object> params){
        return new ResponseEntity<ErrorDTO>(createErrorDTOFromRestError(restErrorSource.toRestError(), params), restErrorSource.toRestError().getHttpStatus());
    }

    public static ResponseEntity<ErrorDTO> createErrorResponseEntity(TranslatableToRestError restErrorSource){
        return new ResponseEntity<ErrorDTO>(createErrorDTOFromRestError(restErrorSource.toRestError()), restErrorSource.toRestError().getHttpStatus());
    }

    public static Map<String, Object> createParams(PNV... PNVs){
        return Arrays.stream(PNVs).collect(Collectors.toMap(PNV::getName, PNV::getValue));
    }
}
