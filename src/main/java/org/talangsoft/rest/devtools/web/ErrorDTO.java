package org.talangsoft.rest.devtools.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import org.talangsoft.rest.devtools.domain.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tamaslang on 20/11/2014.
 */
public class ErrorDTO extends DTO {

    private String errorCode;
    private String errorMessage;
    private Map<String, Object> params = new HashMap<>();


    public ErrorDTO(RestError restError) {
        this(restError.getErrorCode(),restError.getErrorMessage());
    }


    public ErrorDTO(RestError restError, Map<String, Object> params) {
        this(restError.getErrorCode(),restError.getErrorMessage(),params);
    }

    public ErrorDTO(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @JsonCreator
    public ErrorDTO(@JsonProperty("errorCode") String errorCode,@JsonProperty("errorMessage") String errorMessage,@JsonProperty("params") Map<String, Object> params) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        // TODO: create new instance
        this.params = params;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    }