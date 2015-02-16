package org.talang.rest.devtools.api;

/**
 * Interface to mark objects that are identifiable by an ID of any type.
 *
 */
public interface IdentifiableById<T> extends Identifiable{
    T getId();
    void setId(T id);
}
