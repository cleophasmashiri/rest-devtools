package org.talangsoft.rest.devtools.fullapptest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.talangsoft.rest.devtools.fullapptest.bookinventory.api.BookDTO;
import org.talangsoft.rest.devtools.fullapptest.bookinventory.domain.Book;
import org.talangsoft.rest.devtools.fullapptest.bookinventory.gateway.IsbnGateway;
import org.talangsoft.rest.devtools.fullapptest.bookinventory.repository.BookRepository;
import org.talangsoft.rest.devtools.fullapptest.bookinventory.TestApplication;
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
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@ComponentScan({"org.talangsoft.rest.devtools.bookTestapp.service"})
public class BookInventoryWithMockedGatewayTest {

    @Autowired
    IsbnGateway isbnGateway;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    private MockRestServiceServer mockServer;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(isbnGateway.getRestTemplate());
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void testCreateABook() throws Exception {
        mockServer.expect(requestTo(IsbnGateway.ISBN_GATEWAY_ENDPOINT))
                .andRespond(withSuccess("isbn1234", MediaType.APPLICATION_JSON));
        ResultActions resultActions = mockMvc.perform(post("/api/books").content(objectMapper.writeValueAsString(new BookDTO(null,"title","author"))).contentType(MediaType.APPLICATION_JSON_VALUE));
        // validate header
        assertEquals("http://localhost/api/books/isbn1234", resultActions.andReturn().getResponse().getHeader("location"));
        // validate db
        Book book = bookRepository.findOne("isbn1234");
        assertNotNull(book);
        assertEquals("title", book.getTitle());
        assertEquals("author",book.getAuthor());
        assertEquals("isbn1234",book.getIsbn());
    }

    @Test
    public void testCreateABookGatewayThrowsException() throws Exception {
        mockServer.expect(requestTo(IsbnGateway.ISBN_GATEWAY_ENDPOINT))
                .andRespond(withStatus(HttpStatus.CONFLICT));
        ResultActions resultActions = mockMvc.perform(post("/api/books").content(objectMapper.writeValueAsString(new BookDTO(null, "title","author"))).contentType(MediaType.APPLICATION_JSON_VALUE));
        // validate header
        resultActions.andExpect(status().is4xxClientError()).andExpect(content().string("{\"errorCode\":\"GENERAL_GATEWAY_ERROR\",\"errorMessage\":\"Conflict\",\"params\":{\"statusText\":\"Conflict\",\"message\":\"409 Conflict\"}}"));
    }

}
