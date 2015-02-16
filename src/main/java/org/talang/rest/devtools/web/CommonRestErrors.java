package org.talang.rest.devtools.web;

import org.springframework.http.HttpStatus;

/**
 *
 */
public enum CommonRestErrors implements TranslatableToRestError {

    // general errors
    GENERAL_MEDIA_TYPE_NOT_SUPPORTED("Media type not supported",HttpStatus.NOT_FOUND),
    GENERAL_HTTP_MESSAGE_NOT_READABLE("Http message not readable",HttpStatus.NOT_FOUND),
    GENERAL_METHOD_ARGUMENT_NOT_VALID("Method argument not valid",HttpStatus.NOT_FOUND),
    GENERAL_SERVICE_ERROR("Internal Service Error",HttpStatus.INTERNAL_SERVER_ERROR),
    GENERAL_UPDATE_CODE_CONFLICT("The code is different in the payload than the one in the url.", HttpStatus.CONFLICT);


    CommonRestErrors(String errorMessage, HttpStatus httpStatus){
        this.httpStatus=httpStatus;
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    private HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public RestError toRestError(){
        return new RestError(this.name(), errorMessage,httpStatus);
    }
}
