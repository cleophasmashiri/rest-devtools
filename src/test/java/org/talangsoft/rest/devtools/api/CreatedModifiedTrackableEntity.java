package org.talangsoft.rest.devtools.api;

import org.joda.time.DateTime;

/**
 * Created by Tamas Lang
 */
public class CreatedModifiedTrackableEntity implements CreatedModifiedTrackable<Long> {

    private Long createdBy;

    private Long modifiedBy;

    private DateTime createdOn;

    private DateTime modifiedOn;

    @Override
    public Long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(Long createdBy) {
      this.createdBy = createdBy;
    }

    @Override
    public DateTime getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(DateTime createdOn) {
      this.createdOn = createdOn;
    }

    @Override
    public Long getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public DateTime getModifiedOn() {
        return modifiedOn;
    }

    @Override
    public void setModifiedOn(DateTime dateCreated) {
        this.modifiedOn = modifiedOn;
    }
}
