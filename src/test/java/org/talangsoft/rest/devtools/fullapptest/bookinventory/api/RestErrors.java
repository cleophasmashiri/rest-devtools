package org.talangsoft.rest.devtools.fullapptest.bookinventory.api;

import org.springframework.http.HttpStatus;
import org.talangsoft.rest.devtools.web.RestError;
import org.talangsoft.rest.devtools.web.TranslatableToRestError;

public enum RestErrors implements TranslatableToRestError {
    BOOK_NOT_FOUND("The book was not found.", HttpStatus.NOT_FOUND);


    RestErrors(String errorMessage, HttpStatus httpStatus){
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
