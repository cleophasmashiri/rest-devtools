package org.talang.rest.devtools.api;

/**
 * Interface to support modified on data tricking
 *
 * @author Tamas Lang
 *
 */
public interface CreatedByTrackable {

    String CREATED_BY_FIELD_NAME = "created_by";

    String getCreatedBy();

    void setCreatedBy(String createdBy);

}