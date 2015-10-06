package org.motechproject.csd.domain;

import org.motechproject.csd.constants.CSDConstants;
import org.motechproject.mds.annotations.Access;
import org.motechproject.mds.annotations.Cascade;
import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;
import org.motechproject.mds.annotations.UIDisplayable;
import org.motechproject.mds.util.SecurityMode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Java class for address complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressLine" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="component" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
public class Address extends AbstractID {

    @UIDisplayable(position = 1)
    @Field(required = true, tooltip = "These are the lines associated with this type of address.")
    @Cascade(delete = true)
    private Set<AddressLine> addressLines = new HashSet<>();

    @UIDisplayable(position = 0)
    @Field(tooltip = "This is the type of address being entered (i.e. Legal, Mailing, Billing).")
    private String type;

    public Address() {
    }

    public Address(String type) {
        this.type = type;
    }

    public Address(Set<AddressLine> addressLines, String type) {
        this.addressLines = addressLines;
        this.type = type;
    }

    public Set<AddressLine> getAddressLines() {
        return addressLines;
    }

    @XmlElement(name = "addressLine", required = true)
    public void setAddressLines(Set<AddressLine> addressLines) {
        this.addressLines = addressLines;
    }

    public String getType() {
        return type;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }

        Address address = (Address) o;

        if (!addressLines.equals(address.addressLines)) {
            return false;
        }
        if (type != null ? !type.equals(address.type) : address.type != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressLines.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        if (type != null && !type.isEmpty()) {
            return type + ": " + addressLines;
        }
        return addressLines.toString();
    }
}
