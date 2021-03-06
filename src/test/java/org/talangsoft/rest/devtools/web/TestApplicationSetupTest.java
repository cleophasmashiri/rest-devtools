package org.talangsoft.rest.devtools.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.talangsoft.rest.devtools.fullapptest.bookinventory.TestApplication;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
public class TestApplicationSetupTest {
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void contextSetupTest(){
        assertThat(restTemplate,notNullValue());
    }
}
