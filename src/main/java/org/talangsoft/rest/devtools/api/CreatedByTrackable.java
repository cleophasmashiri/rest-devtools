package org.talangsoft.rest.devtools.api;

/**
 * Interface to support modified on data tricking
 *
 * @author Tamas Lang
 *
 */
public interface CreatedByTrackable<T> {

    String CREATED_BY_FIELD_NAME = "created_by";

    T getCreatedBy();

    void setCreatedBy(T createdBy);

}