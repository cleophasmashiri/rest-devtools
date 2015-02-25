package org.talangsoft.rest.devtools.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.talangsoft.rest.devtools.logging.Loggable;

import java.util.List;

@Configuration
public class RestConfiguration extends WebMvcConfigurerAdapter implements Loggable {

    @Autowired
    private Environment env;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public RestTemplate restTemplate() {
        int readTimeout = Integer.parseInt(env.getProperty("gateway.configuration.readTimeout"));
        int connectTimeout = Integer.parseInt(env.getProperty("gateway.configuration.connectTimeout"));
        logger().debug("Create RestTemplate with readTimeout={}, connectTimeout={}", readTimeout, connectTimeout);
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(connectTimeout);
        httpRequestFactory.setReadTimeout(readTimeout);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter());
        return restTemplate;
    }


    private ObjectMapper objectMapper() {
        JodaModule jodaModule = new JodaModule();
        objectMapper.registerModule(new JodaModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        return objectMapper;
    }

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //converters.add(mappingJackson2HttpMessageConverter());
    }

}
