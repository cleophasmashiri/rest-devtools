package org.talang.rest.devtools.fullapptest.bookinventory.config;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(value = {DatabaseConfiguration.REPOSITORY_LOCATION}, entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class DatabaseConfiguration implements EnvironmentAware {

    public static final String REPOSITORY_LOCATION = "org.talang.rest.devtools.repository";

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfiguration.class);
    public static final String CHANGELOG_LOCATION = "classpath:config/liquibase/db-changelog.xml";

    @Resource
    private Environment environment;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    @Bean(name = "liquibase")
    public SpringLiquibase liquibase() {
        LOGGER.debug("Configuring Liquibase for Ref data");
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(CHANGELOG_LOCATION);

        return liquibase;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

