package org.talangsoft.rest.devtools.web.util;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PNVTest {

    @Test
    public void toParamNameValueTest(){
        PNV pnv = PNV.toPNV("name", "value");
        assertThat(pnv.getName(),is("name"));
        assertThat(pnv.getValue(),is("value"));
    }

}
