package org.motechproject.csd.domain;

import org.motechproject.csd.constants.CSDConstants;
import org.motechproject.mds.annotations.Access;
import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;
import org.motechproject.mds.annotations.UIDisplayable;
import org.motechproject.mds.util.SecurityMode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for otherID complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="otherID">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="code" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="assigningAuthorityName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@Entity
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
@Access(value = SecurityMode.PERMISSIONS, members = {CSDConstants.MANAGE_CSD})
public class OtherID extends AbstractID {

    @UIDisplayable(position = 0)
    @Field(required = true, tooltip = "Enter the other ID.")
    private String code;

    @UIDisplayable(position = 1)
    @Field(required = true, tooltip = "Enter the name of the authority that assigned this ID.")
    private String assigningAuthorityName;

    public OtherID() {
    }

    public OtherID(String code) {
        this.code = code;
    }

    public OtherID(String code, String assigningAuthorityName) {
        this.code = code;
        this.assigningAuthorityName = assigningAuthorityName;
    }

    public String getAssigningAuthorityName() {
        return assigningAuthorityName;
    }

    @XmlAttribute(required = true)
    public void setAssigningAuthorityName(String assigningAuthorityName) {
        this.assigningAuthorityName = assigningAuthorityName;
    }

    public String getCode() {
        return code;
    }

    @XmlAttribute(required = true)
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OtherID otherID = (OtherID) o;

        if (!assigningAuthorityName.equals(otherID.assigningAuthorityName)) {
            return false;
        }
        if (!code.equals(otherID.code)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + assigningAuthorityName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return code;
    }
}
