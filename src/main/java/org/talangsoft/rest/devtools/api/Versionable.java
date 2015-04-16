package org.talangsoft.rest.devtools.api;

/**
 * Interface for versionable objects
 *
 */
public interface Versionable {

    String VERSION_FIELD_NAME = "version";

    public Integer getVersion();

    public void setVersion(Integer version);

}
