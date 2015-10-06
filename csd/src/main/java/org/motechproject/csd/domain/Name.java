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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Java class for name complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="name">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="commonName" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="honorific" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="forename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherName" type="{urn:ihe:iti:csd:2013}codedtype" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="suffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = { "commonNames", "honorific", "forename", "otherNames", "surname", "suffix" })
@Access(value = SecurityMode.PERMISSIONS, members = {CSDConstants.MANAGE_CSD})
public class Name extends AbstractID {

    @UIDisplayable(position = 0)
    @Field(required = true, tooltip = "Common names associated with this individual (i.e. nickname).")
    @Cascade(delete = true)
    private List<String> commonNames = new ArrayList<>();

    @UIDisplayable(position = 1)
    @Field(tooltip = "Honorific names given to this individual (i.e. Dr.)")
    private String honorific;

    @UIDisplayable(position = 2)
    @Field(tooltip = "This individual's first (given) name.")
    private String forename;

    @UIDisplayable(position = 3)
    @Field(name = "name_other_names", tooltip = "Other names associated with this individual (i.e. married name).")
    @Cascade(delete = true)
    private Set<CodedType> otherNames = new HashSet<>();

    @UIDisplayable(position = 4)
    @Field(tooltip = "This individual's last name (surname).")
    private String surname;

    @UIDisplayable(position = 5)
    @Field(tooltip = "Additional information about the individual (i.e. Jr, III, PhD, MD)")
    private String suffix;

    public Name() {
    }

    public Name(List<String> commonNames) {
        this.commonNames = commonNames;
    }

    public Name(List<String> commonNames, String honorific, String forename, Set<CodedType> otherNames, String surname, String suffix) {
        this.commonNames = commonNames;
        this.honorific = honorific;
        this.forename = forename;
        this.otherNames = otherNames;
        this.surname = surname;
        this.suffix = suffix;
    }

    public List<String> getCommonNames() {
        return commonNames;
    }

    @XmlElement(name = "commonName", required = true)
    public void setCommonNames(List<String> commonNames) {
        this.commonNames = commonNames;
    }

    public String getHonorific() {
        return honorific;
    }

    @XmlElement
    public void setHonorific(String honorific) {
        this.honorific = honorific;
    }

    public String getForename() {
        return forename;
    }

    @XmlElement
    public void setForename(String forename) {
        this.forename = forename;
    }

    public Set<CodedType> getOtherNames() {
        return otherNames;
    }

    @XmlElement(name = "otherName")
    public void setOtherNames(Set<CodedType> otherNames) {
        this.otherNames = otherNames;
    }

    public String getSurname() {
        return surname;
    }

    @XmlElement
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSuffix() {
        return suffix;
    }

    @XmlElement
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override //NO CHECKSTYLE CyclomaticComplexity
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Name name = (Name) o;

        if (!commonNames.equals(name.commonNames)) {
            return false;
        }
        if (forename != null ? !forename.equals(name.forename) : name.forename != null) {
            return false;
        }
        if (honorific != null ? !honorific.equals(name.honorific) : name.honorific != null) {
            return false;
        }
        if (otherNames != null ? !otherNames.equals(name.otherNames) : name.otherNames != null) {
            return false;
        }
        if (suffix != null ? !suffix.equals(name.suffix) : name.suffix != null) {
            return false;
        }
        if (surname != null ? !surname.equals(name.surname) : name.surname != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = commonNames.hashCode();
        result = 31 * result + (honorific != null ? honorific.hashCode() : 0);
        result = 31 * result + (forename != null ? forename.hashCode() : 0);
        result = 31 * result + (otherNames != null ? otherNames.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return commonNames.toString();
    }
}
