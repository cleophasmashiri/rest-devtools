package org.talang.rest.devtools.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Interface that captures the logging concern and provides a default implementation.
 *
 * source: http://www.opencredo.com/2015/01/30/traits-java-8-default-methods/
 */
public interface Loggable {

    default Logger logger() {
        return LoggerFactory.getLogger(this.getClass());
    }
}
