package org.talangsoft.rest.devtools.web;

import org.talangsoft.rest.devtools.logging.Loggable;
import org.talangsoft.rest.devtools.web.util.RestUtils;
import org.talangsoft.rest.devtools.gateway.GatewayException;
import org.talangsoft.rest.devtools.web.util.PNV;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Special Controller for handling validation errors in the REST API requests.
 * Very much based on this article: http://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-adding-validation-to-a-rest-api/
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler implements Loggable {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        ErrorDTO errorDTO = RestUtils.createErrorDTOFromRestError(CommonRestErrors.GENERAL_METHOD_ARGUMENT_NOT_VALID.toRestError(), null, ex);
        errorDTO.getParams().put("fieldErrors", fieldErrors);
        errorDTO.getParams().put("globalErrors", globalErrors);
        errorDTO.getParams().put("errors", errors);
        return new ResponseEntity(errorDTO, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status,
                                                                     WebRequest request) {
        String unsupported = "Unsupported content type: " + ex.getContentType();
        String supported = "Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes());

        ErrorDTO errorDTO = RestUtils.createErrorDTOFromRestError(CommonRestErrors.GENERAL_MEDIA_TYPE_NOT_SUPPORTED.toRestError(),null,ex);

        errorDTO.getParams().put("UnsupportedContentType", unsupported);
        errorDTO.getParams().put("supportedContentType", supported);
        return new ResponseEntity(errorDTO, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        ErrorDTO errorDTO;
        if (mostSpecificCause != null) {
            errorDTO = RestUtils.createErrorDTOFromRestError(CommonRestErrors.GENERAL_MEDIA_TYPE_NOT_SUPPORTED.toRestError(),null, ex);
        } else {
            errorDTO = RestUtils.createErrorDTOFromRestError(CommonRestErrors.GENERAL_MEDIA_TYPE_NOT_SUPPORTED.toRestError(),null, ex);
        }
        return new ResponseEntity(errorDTO, headers, status);
    }

    @ExceptionHandler(RestException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleRestException(RestException ex){
        logger().debug("Handling rest exception", ex);
        return new ResponseEntity<ErrorDTO>(RestUtils.createErrorDTOFromRestError(ex.toRestError(), ex.getErrorParams()), ex.toRestError().getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleException(Exception ex){
        logger().debug("Handling exception", ex);
        return new ResponseEntity<ErrorDTO>(RestUtils.createErrorDTOFromRestError(CommonRestErrors.GENERAL_SERVICE_ERROR.toRestError(),
                RestUtils.createParams(PNV.toPNV("details", ex.getMessage()))),
                CommonRestErrors.GENERAL_SERVICE_ERROR.getHttpStatus());
    }

    @ExceptionHandler(GatewayException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleGatewayException(GatewayException ex){
        logger().debug("Handling gateway exception", ex);
        return new ResponseEntity<ErrorDTO>(ex.getErrorDTO(), ex.getHttpStatus());
    }

}

