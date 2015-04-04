package org.talangsoft.rest.devtools.api;

/**
 * Interface to support soft deletion
 *
 * @author Tamas Lang
 *
 */
public interface SoftDeletable {

    String DELETED_FIELD_NAME = "deleted";

    boolean isDeleted();

    void setDeleted(boolean deleted);

}