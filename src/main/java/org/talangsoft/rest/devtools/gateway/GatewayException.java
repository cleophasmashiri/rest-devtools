package org.talangsoft.rest.devtools.gateway;

import org.talangsoft.rest.devtools.web.ErrorDTO;
import org.springframework.http.HttpStatus;

/**
 * Created by tamaslang on 26/11/14.
 */
public class GatewayException extends RuntimeException {
    private ErrorDTO errorDTO;

    private HttpStatus httpStatus;

    private static final String DEFAULT_ERROR_MSG = "ErrorDTO is null, might mean unexpected exception";

    public GatewayException(ErrorDTO errorDTO, HttpStatus httpStatus){
        super(errorDTO == null || errorDTO.getErrorMessage() == null ? DEFAULT_ERROR_MSG : errorDTO.getErrorMessage());
        this.errorDTO = errorDTO;
        this.httpStatus = httpStatus;
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }

    public void setErrorDTO(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
