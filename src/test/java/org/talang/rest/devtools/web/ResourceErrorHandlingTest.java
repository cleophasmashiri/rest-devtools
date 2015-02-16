package org.talang.rest.devtools.web;

import org.talang.rest.devtools.testapp.ExampleResource;
import org.talang.rest.devtools.testapp.TestApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
public class ResourceErrorHandlingTest {


    private ExampleResource exampleResource;


    private MockRestServiceServer mockServer;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        // mockMvc = MockMvcBuilders.standaloneSetup (.setMessageConverters(new MappingJackson2HttpMessageConverter()).setHandlerExceptionResolvers(createExceptionResolver()).build();
    }

//    private ExceptionHandlerExceptionResolver createExceptionResolver() {
//        ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
//            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
//                Method method = new ExceptionHandlerMethodResolver(RestExceptionHandler.class).resolveMethod(exception);
//                return new ServletInvocableHandlerMethod(new RestExceptionHandler(), method);
//            }
//        };
//        exceptionResolver.afterPropertiesSet();
//        return exceptionResolver;
//    }

    @Test
    public void testExampleResourceStringlist() throws Exception {
        mockMvc.perform(get("/api/example/stringlist"))
                .andExpect(status().isOk()).andExpect(content().string("[\"a\",\"b\"]"));
    }

    @Test
    public void testExampleResourceTakeStringlistWithWrongDat() throws Exception {
        mockMvc.perform(post("/api/example/stringlist").content("{}"))
                .andExpect(status().is(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())).andExpect(content()
                .string("{\"errorCode\":\"GENERAL_MEDIA_TYPE_NOT_SUPPORTED\",\"errorMessage\":\"Media type not supported\",\"params\":{\"UnsupportedContentType\":\"Unsupported content type: null\",\"supportedContentType\":\"Supported content types: application/json\",\"exceptionMessage\":\"Content type 'null' not supported\"}}"));
    }




    @Test
    public void testExampleResourceExceptionRestException() throws Exception{
        mockMvc.perform(get("/api/example/exception/restexception"))
                .andExpect(status().is5xxServerError()).andExpect(content().string("{\"errorCode\":\"GENERAL_SERVICE_ERROR\",\"errorMessage\":\"Internal Service Error\",\"params\":{}}"));
    }

    @Test
    public void testExampleResourceExceptionNpe() throws Exception{
        mockMvc.perform(get("/api/example/exception/npe"))
                .andExpect(status().is5xxServerError()).andExpect(content().string("{\"errorCode\":\"GENERAL_SERVICE_ERROR\",\"errorMessage\":\"Internal Service Error\",\"params\":{\"details\":\"Null pointer ex!\"}}"));
    }

    @Test
    public void testExampleResourceNotExistingEndpoint() throws Exception{
        mockMvc.perform(get("/api/example/not_existing"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testExampleResourceExceptionGatewayException() throws Exception{
        mockMvc.perform(get("/api/example/exception/gatewayexception"))
                .andExpect(status().is(409)).andExpect(content().string("{\"errorCode\":\"GENERAL_UPDATE_CODE_CONFLICT\",\"errorMessage\":\"The code is different in the payload than the one in the url.\",\"params\":{}}"));
    }

}
