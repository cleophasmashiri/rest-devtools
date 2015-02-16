package org.talang.rest.devtools.converter;

import org.talang.rest.devtools.domain.DTO;

public class ADTO extends DTO {
    private String text;

    private String name;

    private String dtoOnlyProperty;

    public ADTO(String name, String text, String dtoOnlyProperty) {
        this.text = text;
        this.name = name;
        this.dtoOnlyProperty = dtoOnlyProperty;
    }

    public ADTO() {
    }

    public String getDtoOnlyProperty() {
        return dtoOnlyProperty;
    }

    public void setDtoOnlyProperty(String dtoOnlyProperty) {
        this.dtoOnlyProperty = dtoOnlyProperty;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
