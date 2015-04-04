package org.talangsoft.rest.devtools.api;

import org.joda.time.DateTime;

/**
 * Interface to support modified on data tracking
 *
 * @author Tamas Lang
 *
 */
public interface ModifiedOnTrackable {

    String MODIFIED_ON_FIELD_NAME = "modified_on";

    DateTime getModifiedOn();

    void setModifiedOn(DateTime dateCreated);
}