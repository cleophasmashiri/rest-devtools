package org.talang.rest.devtools.domain;

import org.talang.rest.devtools.api.Identifiable;
import org.talang.rest.devtools.logging.ToStringAllFieldsSupport;

/**
 * Abstract entity might be the parent of all JPA entity type which requires its fields to be logged automatically
 */
public abstract class AbstractEntity extends ToStringAllFieldsSupport implements Identifiable {

}
