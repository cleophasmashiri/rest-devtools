package org.talang.rest.devtools.testapp;

import org.talang.rest.devtools.config.RestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RestConfiguration.class)
@ComponentScan(basePackages = {"org.talang.rest.devtools"})
public class RestDevtoolsConfig {

}
