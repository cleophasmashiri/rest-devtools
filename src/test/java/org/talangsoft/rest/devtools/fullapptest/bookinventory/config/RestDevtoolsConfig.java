package org.talangsoft.rest.devtools.fullapptest.bookinventory.config;

import org.talangsoft.rest.devtools.config.RestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RestConfiguration.class)
@ComponentScan(basePackages = {"org.talangsoft.rest.devtools"})
public class RestDevtoolsConfig {

}
