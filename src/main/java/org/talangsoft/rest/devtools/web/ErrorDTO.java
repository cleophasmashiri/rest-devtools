package org.talangsoft.rest.devtools.web;

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

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    private Object payload;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
