package org.talangsoft.rest.devtools.api;

import org.joda.time.DateTime;

/**
 * Interface to support modified on data tricking
 *
 * @author Tamas Lang
 *
 */
public interface CreatedOnTrackable {

    String CREATED_ON_FIELD_NAME = "created_on";

    DateTime getCreatedOn();

    void setCreatedOn(DateTime createdOn);
}