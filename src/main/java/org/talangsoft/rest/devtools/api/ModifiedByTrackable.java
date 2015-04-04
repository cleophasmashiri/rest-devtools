package org.talangsoft.rest.devtools.api;

import org.joda.time.DateTime;

/**
 * Interface to support modified on data tracking
 *
 * @author Tamas Lang
 *
 */
public interface ModifiedByTrackable<T> {

    String MODIFIED_BY_FIELD_NAME = "modified_by";

    T getModifiedBy();

    void setModifiedBy(T modifiedBy);
}