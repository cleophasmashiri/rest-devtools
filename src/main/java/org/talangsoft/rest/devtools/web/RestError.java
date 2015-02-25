package org.talangsoft.rest.devtools.web;

import org.springframework.http.HttpStatus;

/**
 * Created by tamaslang on 26/11/14.
 */
public final class RestError {

    private String errorCode;

    private String errorMessage;

    private HttpStatus httpStatus;

    public RestError(String errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
