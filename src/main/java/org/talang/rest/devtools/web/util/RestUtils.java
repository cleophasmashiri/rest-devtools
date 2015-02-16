package org.talang.rest.devtools.web.util;

import org.talang.rest.devtools.web.ErrorDTO;
import org.talang.rest.devtools.web.RestError;
import org.talang.rest.devtools.web.TranslatableToRestError;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tamaslang on 26/11/14.
 */
public class RestUtils {

    private RestUtils() {
        // utility class
    }

    public static ErrorDTO createErrorDTOFromRestError(RestError restError){
        return createErrorDTOFromRestError(restError,null);
    }

    public static  ErrorDTO createErrorDTOFromRestError(RestError restError, Map<String,Object> params){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode(restError.getErrorCode());
        errorDTO.setErrorMessage(restError.getErrorMessage());
        if(params != null) {
            errorDTO.setParams(params);
        }
        return errorDTO;
    }

    public static  ErrorDTO createErrorDTOFromRestError(RestError restError, Map<String,Object> params, Throwable ex){
        ErrorDTO errorDTO = createErrorDTOFromRestError(restError,params);
        errorDTO.getParams().put("exceptionMessage",ex.getMessage());
        return errorDTO;
    }

    public static ResponseEntity<ErrorDTO> createErrorResponseEntity(TranslatableToRestError restErrorSource, Map<String,Object> params){
        return new ResponseEntity<ErrorDTO>(createErrorDTOFromRestError(restErrorSource.toRestError(), params), restErrorSource.toRestError().getHttpStatus());
    }

    public static ResponseEntity<ErrorDTO> createErrorResponseEntity(TranslatableToRestError restErrorSource){
        return new ResponseEntity<ErrorDTO>(createErrorDTOFromRestError(restErrorSource.toRestError()), restErrorSource.toRestError().getHttpStatus());
    }

    public static Map<String, Object> createParams(PNV... PNVs){
        Map<String, Object> paramMap = new HashMap<>();
        for(PNV pnv : PNVs){
            paramMap.put(pnv.getName(),pnv.getValue());
        }
        return paramMap;
    }
}
