package org.talang.rest.devtools.testapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

@EnableAutoConfiguration
@Import(RestDevtoolsConfig.class)
public class TestApplication {
    @Resource
    private Environment env;

    private static final Logger LOGGER = LoggerFactory.getLogger(TestApplication.class);

    private RelaxedPropertyResolver dataSourcePropertyResolver;

    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            LOGGER.warn("No Spring profile configured, running with default configuration");
        } else {
            LOGGER.info("Running with Spring profile(s) : {}", env.getActiveProfiles());
            this.dataSourcePropertyResolver = new RelaxedPropertyResolver(env, "jmx.");
        }
    }



    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TestApplication.class);
        app.setShowBanner(false);
        app.run(args);
    }

}
