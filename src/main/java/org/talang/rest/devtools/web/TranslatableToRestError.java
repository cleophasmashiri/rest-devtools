package org.talang.rest.devtools.web;

/**
 * Interface for objects which can be translated to rest errors
 */

public interface TranslatableToRestError {
    RestError toRestError();
}
