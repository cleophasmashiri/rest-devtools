package org.talangsoft.rest.devtools.domain;

import org.talangsoft.rest.devtools.api.Identifiable;
import org.talangsoft.rest.devtools.logging.ToStringAllFieldsSupport;

/**
 * Abstract entity might be the parent of all JPA entity type which requires its fields to be logged automatically
 */
public abstract class AbstractEntity extends ToStringAllFieldsSupport implements Identifiable {

}
