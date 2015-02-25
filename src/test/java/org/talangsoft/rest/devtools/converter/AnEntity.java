package org.talangsoft.rest.devtools.converter;

import org.talangsoft.rest.devtools.domain.AbstractEntity;

public class AnEntity extends AbstractEntity {

    private String name;
    private String description;
    private String entityOnlyProperty;

    public AnEntity(String name, String description, String entityOnlyProperty) {
        this.name = name;
        this.description = description;
        this.entityOnlyProperty = entityOnlyProperty;
    }

    public AnEntity() {
    }

    public String getEntityOnlyProperty() {
        return entityOnlyProperty;
    }

    public void setEntityOnlyProperty(String entityOnlyProperty) {
        this.entityOnlyProperty = entityOnlyProperty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
