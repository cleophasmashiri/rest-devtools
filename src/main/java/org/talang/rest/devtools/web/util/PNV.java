package org.talang.rest.devtools.web.util;

public class PNV {
    private String name;
    private Object value;

    private PNV(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public static PNV toPNV(String name, Object value){
        return new PNV(name, value);
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}
