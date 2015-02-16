package org.talang.rest.devtools.api;

/**
 * Interface to support modified on data tricking
 *
 * @author Tamas Lang
 *
 */
public interface SoftDeletable {

    String DELETED_FIELD_NAME = "deleted";

    boolean isDeleted();

    void setDeleted(boolean deleted);

}