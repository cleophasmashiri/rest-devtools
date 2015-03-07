package org.talangsoft.rest.devtools.domain;

import com.google.common.base.Objects;
import org.talangsoft.rest.devtools.api.Identifiable;
import org.talangsoft.rest.devtools.logging.ToStringAllFieldsSupport;
import org.springframework.data.neo4j.annotation.GraphId;

/**
 * Common SDN object to use when creating a SDN type
 * ToString method is provided which works with reflection
 *
 */
public abstract class SDN extends ToStringAllFieldsSupport implements Identifiable {

    private static final long serialVersionUID = 1784244125987813122L;

    @GraphId
    private Long internalNodeId;

    // graph Id must be included in equals:
    // http://docs.spring.io/spring-data/neo4j/docs/current/reference/html
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        if(internalNodeId == null){
            return false;
        }
        return Objects.equal(this.internalNodeId, ((SDN) other).internalNodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(internalNodeId);
    }

    public Long getInternalNodeId() {
        return internalNodeId;
    }

    public void setInternalNodeId(Long internalNodeId) {
        this.internalNodeId = internalNodeId;
    }
}
