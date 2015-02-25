package org.talangsoft.rest.devtools.logging;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/*
  ToString method is provided which works with reflection
 */
public abstract class ToStringAllFieldsSupport {
    // we have to exclude passwords from log
    private static final String[] EXCLUDED_FIELD_NAMES_FROM_TOSTRING =  { "password" };

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).setExcludeFieldNames(EXCLUDED_FIELD_NAMES_FROM_TOSTRING).toString();
    }
}
