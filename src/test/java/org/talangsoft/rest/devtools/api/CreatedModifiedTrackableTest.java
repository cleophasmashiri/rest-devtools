package org.talangsoft.rest.devtools.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by admin on 04/04/15.
 */
public class CreatedModifiedTrackableTest {
    CreatedModifiedTrackable<Long> entity;


    @Before
    public void setUp(){
        entity = new CreatedModifiedTrackableEntity();
    }

    @Test
    public void setCreatedModifiedDate(){
       // TODO: use gettersetter test helper class later
        DateTime createdDate = new DateTime(2015,04,4,14,9);
        entity.setCreatedOn(createdDate);
        assertThat(entity.getCreatedOn(),is(createdDate));
    }
}
