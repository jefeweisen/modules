package org.motechproject.csd.domain;

import org.motechproject.csd.constants.CSDConstants;
import org.motechproject.mds.annotations.Access;
import org.motechproject.mds.annotations.Cascade;
import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;
import org.motechproject.mds.annotations.Ignore;
import org.motechproject.mds.annotations.UIDisplayable;
import org.motechproject.mds.util.SecurityMode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Java class for facility complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="facility">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ihe:iti:csd:2013}uniqueID">
 *       &lt;sequence>
 *         &lt;element name="otherID" type="{urn:ihe:iti:csd:2013}otherID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="codedType" type="{urn:ihe:iti:csd:2013}codedtype" maxOccurs="unbounded"/>
 *         &lt;element name="primaryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otherName" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="address" type="{urn:ihe:iti:csd:2013}address" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contact" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="provider" type="{urn:ihe:iti:csd:2013}uniqueID"/>
 *                   &lt;element name="person" type="{urn:ihe:iti:csd:2013}person"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="geocode" type="{urn:ihe:iti:csd:2013}geocode" minOccurs="0"/>
 *         &lt;element name="language" type="{urn:ihe:iti:csd:2013}codedtype" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contactPoint" type="{urn:ihe:iti:csd:2013}contactPoint" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="organizations" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="organization" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{urn:ihe:iti:csd:2013}uniqueID">
 *                           &lt;sequence>
 *                             &lt;element name="service" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;extension base="{urn:ihe:iti:csd:2013}uniqueID">
 *                                     &lt;sequence>
 *                                       &lt;element name="name" type="{urn:ihe:iti:csd:2013}name" maxOccurs="unbounded" minOccurs="0"/>
 *                                       &lt;element name="language" type="{urn:ihe:iti:csd:2013}codedtype" maxOccurs="unbounded" minOccurs="0"/>
 *                                       &lt;element name="operatingHours" type="{urn:ihe:iti:csd:2013}operatingHours" maxOccurs="unbounded" minOccurs="0"/>
 *                                       &lt;element name="freeBusyURI" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *                                       &lt;element name="extension" type="{urn:ihe:iti:csd:2013}extension" maxOccurs="unbounded" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/extension>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="extension" type="{urn:ihe:iti:csd:2013}extension" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="operatingHours" type="{urn:ihe:iti:csd:2013}operatingHours" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extension" type="{urn:ihe:iti:csd:2013}extension" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="record" type="{urn:ihe:iti:csd:2013}record"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@Entity(maxFetchDepth = 4)
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = { "otherIDs", "codedTypes", "primaryName", "otherNames", "addresses", "contacts", "geocode", "languages", "contactPoints", "facilityOrganizationSet", "operatingHours", "extensions", "record" })
@Access(value = SecurityMode.PERMISSIONS, members = {CSDConstants.MANAGE_CSD})
public class Facility extends BaseMainEntity {

    @Field(tooltip = "This facility’s geocoded point position (GPS coordinates), if known.")
    @Cascade(delete = true)
    private Geocode geocode;

    @Field(tooltip = "The “parent” organization of this facility. There may be more than one parent organization for a facility.")
    @Cascade(delete = true)
    private Set<FacilityOrganization> facilityOrganizations = new HashSet<>();

    @UIDisplayable(position = 2)
    @Field(name = "facility_operating_hours", tooltip = "The operating hours for this facility, if known. This indicates " +
            "the open hours, days of the week, and any shutdown periods (e.g., civic or national holidays, etc.).")
    @Cascade(delete = true)
    private Set<OperatingHours> operatingHours = new HashSet<>();

    @Field(name = "facility_other_ids", tooltip = "Other identifiers for this facility.")
    @Cascade(delete = true)
    private Set<OtherID> otherIDs = new HashSet<>();

    @UIDisplayable(position = 0)
    @Field(required = true, tooltip = "This facility’s name, for primary use.")
    private String primaryName;

    @Field(name = "facility_other_names", tooltip = "Other optional names for this facility.")
    @Cascade(delete = true)
    private Set<OtherName> otherNames = new HashSet<>();

    @UIDisplayable(position = 1)
    @Field(name = "facility_addresses", tooltip = "The address(es) of this facility, if known. More than one address " +
            "may be specified, but the primary address must be indicated as such.")
    @Cascade(delete = true)
    private Set<Address> addresses = new HashSet<>();

    @Field(name = "facility_contacts", tooltip = "The point(s) of contact defined for this facility, if known.")
    @Cascade(delete = true)
    private Set<OrganizationContact> contacts = new HashSet<>();

    @Field(name = "facility_languages", tooltip = "The languages this facility is able to operate in, if known.")
    @Cascade(delete = true)
    private Set<CodedType> languages = new HashSet<>();

    @Field(name = "facility_contact_points", tooltip = "This facility’s contact points (i.e. Business Phone, Fax, " +
            "Encryption Certificate, etc.).")
    @Cascade(delete = true)
    private Set<ContactPoint> contactPoints = new HashSet<>();

    @Field(required = true, name = "facility_coded_types", tooltip = "Facility type as identified by national or regional organizations.")
    @Cascade(delete = true)
    private Set<CodedType> codedTypes = new HashSet<>();

    @Field(name = "facility_extensions", tooltip = "This is a locally defined extension for this entity.")
    @Cascade(delete = true)
    private Set<Extension> extensions = new HashSet<>();

    public Facility() {
    }

    public Facility(String entityID, Set<CodedType> codedTypes, Record record, String primaryName) {
        setEntityID(entityID);
        this.codedTypes = codedTypes;
        setRecord(record);
        this.primaryName = primaryName;
    }

    public Facility(String entityID, Set<CodedType> codedTypes, Set<Extension> extensions, Record record, Set<OtherID> otherIDs, //NO CHECKSTYLE ArgumentCount
                    String primaryName, Set<OtherName> otherNames, Set<Address> addresses, Set<OrganizationContact> contacts, Set<CodedType> languages,
                    Set<ContactPoint> contactPoints, Geocode geocode, Set<FacilityOrganization> facilityOrganizations, Set<OperatingHours> operatingHours) {
        setEntityID(entityID);
        this.codedTypes = codedTypes;
        this.extensions = extensions;
        setRecord(record);
        this.otherIDs = otherIDs;
        this.primaryName = primaryName;
        this.otherNames = otherNames;
        this.addresses = addresses;
        this.contacts = contacts;
        this.languages = languages;
        this.contactPoints = contactPoints;
        this.geocode = geocode;
        this.facilityOrganizations = facilityOrganizations;
        this.operatingHours = operatingHours;
    }

    public Geocode getGeocode() {
        return geocode;
    }

    @XmlElement
    public void setGeocode(Geocode geocode) {
        this.geocode = geocode;
    }

    @XmlTransient
    public Set<FacilityOrganization> getFacilityOrganizations() {
        return facilityOrganizations;
    }

    public void setFacilityOrganizations(Set<FacilityOrganization> facilityOrganizations) {
        this.facilityOrganizations = facilityOrganizations;
    }

    @Ignore
    public Set<FacilityOrganization> getFacilityOrganizationSet() {
        if (facilityOrganizations != null && facilityOrganizations.isEmpty()) {
            return null;
        }
        return facilityOrganizations;
    }

    @XmlElementWrapper(name = "organizations")
    @XmlElement(name = "organization", required = true)
    public void setFacilityOrganizationSet(Set<FacilityOrganization> facilityOrganizationSet) {
        facilityOrganizations = facilityOrganizationSet;
    }

    public Set<OperatingHours> getOperatingHours() {
        return operatingHours;
    }

    @XmlElement
    public void setOperatingHours(Set<OperatingHours> operatingHours) {
        this.operatingHours = operatingHours;
    }

    public Set<OtherID> getOtherIDs() {
        return otherIDs;
    }

    @XmlElement(name = "otherID")
    public void setOtherIDs(Set<OtherID> otherIDs) {
        this.otherIDs = otherIDs;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    @XmlElement(required = true)
    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public Set<OtherName> getOtherNames() {
        return otherNames;
    }

    @XmlElement(name = "otherName")
    public void setOtherNames(Set<OtherName> otherNames) {
        this.otherNames = otherNames;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    @XmlElement(name = "address")
    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<OrganizationContact> getContacts() {
        return contacts;
    }

    @XmlElement(name = "contact")
    public void setContacts(Set<OrganizationContact> contacts) {
        this.contacts = contacts;
    }

    public Set<CodedType> getLanguages() {
        return languages;
    }

    @XmlElement(name = "language")
    public void setLanguages(Set<CodedType> languages) {
        this.languages = languages;
    }

    public Set<ContactPoint> getContactPoints() {
        return contactPoints;
    }

    @XmlElement(name = "contactPoint")
    public void setContactPoints(Set<ContactPoint> contactPoints) {
        this.contactPoints = contactPoints;
    }

    public Set<CodedType> getCodedTypes() {
        return codedTypes;
    }

    @XmlElement(name = "codedType", required = true)
    public void setCodedTypes(Set<CodedType> codedTypes) {
        this.codedTypes = codedTypes;
    }

    public Set<Extension> getExtensions() {
        return extensions;
    }

    @XmlElement(name = "extension")
    public void setExtensions(Set<Extension> extensions) {
        this.extensions = extensions;
    }

    @Override //NO CHECKSTYLE CyclomaticComplexity
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Facility facility = (Facility) o;

        if (!primaryName.equals(facility.primaryName)) {
            return false;
        }
        if (!codedTypes.equals(facility.codedTypes)) {
            return false;
        }
        if (otherIDs != null ? !otherIDs.equals(facility.otherIDs) : facility.otherIDs != null) {
            return false;
        }
        if (otherNames != null ? !otherNames.equals(facility.otherNames) : facility.otherNames != null) {
            return false;
        }
        if (addresses != null ? !addresses.equals(facility.addresses) : facility.addresses != null) {
            return false;
        }
        if (geocode != null ? !geocode.equals(facility.geocode) : facility.geocode != null) {
            return false;
        }
        if (operatingHours != null ? !operatingHours.equals(facility.operatingHours) : facility.operatingHours != null) {
            return false;
        }
        if (languages != null ? !languages.equals(facility.languages) : facility.languages != null) {
            return false;
        }
        if (contactPoints != null ? !contactPoints.equals(facility.contactPoints) : facility.contactPoints != null) {
            return false;
        }
        if (contacts != null ? !contacts.equals(facility.contacts) : facility.contacts != null) {
            return false;
        }
        if (facilityOrganizations != null ? !facilityOrganizations.equals(facility.facilityOrganizations) : facility.facilityOrganizations != null) {
            return false;
        }
        if (extensions != null ? !extensions.equals(facility.extensions) : facility.extensions != null) {
            return false;
        }

        return true;
    }

    @Override //NO CHECKSTYLE CyclomaticComplexity
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (geocode != null ? geocode.hashCode() : 0);
        result = 31 * result + (facilityOrganizations != null ? facilityOrganizations.hashCode() : 0);
        result = 31 * result + (operatingHours != null ? operatingHours.hashCode() : 0);
        result = 31 * result + (otherIDs != null ? otherIDs.hashCode() : 0);
        result = 31 * result + primaryName.hashCode();
        result = 31 * result + (otherNames != null ? otherNames.hashCode() : 0);
        result = 31 * result + (addresses != null ? addresses.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (languages != null ? languages.hashCode() : 0);
        result = 31 * result + (contactPoints != null ? contactPoints.hashCode() : 0);
        result = 31 * result + codedTypes.hashCode();
        result = 31 * result + (extensions != null ? extensions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return primaryName;
    }
}
