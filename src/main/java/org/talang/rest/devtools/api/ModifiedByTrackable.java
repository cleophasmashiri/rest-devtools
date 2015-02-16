package org.talang.rest.devtools.api;

import org.joda.time.DateTime;

/**
 * Interface to support modified on data tricking
 *
 * @author Tamas Lang
 *
 */
public interface ModifiedByTrackable {

    String MODIFIED_BY_FIELD_NAME = "modified_by";

    String getModifiedBy();

    void setModifiedBy(String modifiedBy);
}