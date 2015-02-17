package org.talang.rest.devtools.fullapptest.bookinventory.config;

import org.hsqldb.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HsqlConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(HsqlConfiguration.class);

    /**
     * HSQL Server bean available for remote access.
     *
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Server hsqlServer() {
        try {
            LOGGER.debug("Starting HSQL server");
            Server server = new Server();

            server.setDatabaseName(0, "spectrum-postgres-data");

            server.setTrace(false);
            return server;
        } catch (Exception e) {
            throw new ApplicationContextException("Failed to start HSQL server", e);
        }
    }
}

