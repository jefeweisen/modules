package org.motechproject.csd.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;
import org.motechproject.mds.annotations.Ignore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlTransient
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BaseMainEntity extends AbstractID {

    @Field(required = true, tooltip = "The globally unique identifier")
    private String entityID;

    @Ignore
    private Record record;

    @Field(required = true, tooltip = "The status may be either Active/Inactive. Active means currently providing or willing " +
            "to provide services.")
    private String status;

    @Field(tooltip = "This is the source from where you got this entity information.")
    private String sourceDirectory;

    public String getEntityID() {
        return entityID;
    }

    @XmlAttribute
    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public Record getRecord() {
        if (record == null) {
            record = new Record(getCreationDate(), getModificationDate(), status, sourceDirectory);
        }
        return record;
    }

    @XmlElement(required = true)
    public void setRecord(Record record) {
        this.record = record;
        status = record.getStatus();
        sourceDirectory = record.getSourceDirectory();
        setCreationDate(record.getCreated());
        setModificationDate(record.getUpdated());
    }

    @XmlTransient
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public String getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseMainEntity that = (BaseMainEntity) o;

        if (entityID != null ? !entityID.equals(that.entityID) : that.entityID != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return entityID != null ? entityID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return entityID;
    }
}
