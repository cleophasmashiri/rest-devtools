package org.talangsoft.rest.devtools.web;

import java.util.Map;

/**
 * Created by tamaslang on 20/11/2014.
 */
public class RestException extends RuntimeException implements TranslatableToRestError {

    private RestError restError;

    @Override
    public RestError toRestError() {
        return restError;
    }

    private Map<String, Object> errorParams;

    public RestException(RestError restError, Map<String, Object> params) {
        this(restError);
        this.errorParams = params;
    }

    public RestException(RestError restError) {
        super(restError.getErrorMessage());
        this.restError = restError;
    }

    public Map<String, Object> getErrorParams() {
        return errorParams;
    }

}
