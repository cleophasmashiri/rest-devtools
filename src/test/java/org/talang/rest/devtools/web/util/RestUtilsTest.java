package org.talang.rest.devtools.web.util;

import org.talang.rest.devtools.web.RestError;
import org.talang.rest.devtools.web.ErrorDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RestUtilsTest {

    @Test
    public void createParamsTest(){
        Map<String,Object> params = RestUtils.createParams(
                PNV.toPNV("name1", "value1"),
                PNV.toPNV("name2", "value2"));
        assertEquals("value1", params.get("name1"));
        assertEquals("value2", params.get("name2"));
    }

    public void createErrorDTOFromRestErrorTest(){
        RestError restError = new RestError("code","message", HttpStatus.BAD_REQUEST);
        Map<String,Object> params = new HashMap<>();

        ErrorDTO errorDTO = RestUtils.createErrorDTOFromRestError(restError, params, new Exception("exmessage"));
        assertEquals(HttpStatus.BAD_REQUEST, errorDTO.getErrorCode());
        assertEquals("message", errorDTO.getErrorMessage());
        assertEquals(params, errorDTO.getParams());
        assertEquals("exmessage", errorDTO.getParams().get("exceptionMessage"));
    }
}
