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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Java class for organization complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="organization">
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
 *         &lt;element name="credential" type="{urn:ihe:iti:csd:2013}credential" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="language" type="{urn:ihe:iti:csd:2013}codedtype" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="specialization" type="{urn:ihe:iti:csd:2013}codedtype" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contactPoint" type="{urn:ihe:iti:csd:2013}contactPoint" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="parent" type="{urn:ihe:iti:csd:2013}uniqueID" minOccurs="0"/>
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
@XmlType(propOrder = { "otherIDs", "codedTypes", "primaryName", "otherNames", "addresses", "contacts", "credentials", "languages", "specializations", "contactPoints", "parent", "extensions", "record" })
@Access(value = SecurityMode.PERMISSIONS, members = {CSDConstants.MANAGE_CSD})
public class Organization extends BaseMainEntity {

    @Field(name = "organization_other_ids", tooltip = "Other identifiers for this organization.")
    @Cascade(delete = true)
    private Set<OtherID> otherIDs = new HashSet<>();

    @UIDisplayable(position = 0)
    @Field(required = true, tooltip = "The organization’s preferred name.")
    private String primaryName;

    @Field(name = "organization_other_names", tooltip = "Other names for the organization.")
    @Cascade(delete = true)
    private Set<OtherName> otherNames = new HashSet<>();

    @UIDisplayable(position = 1)
    @Field(name = "organization_addresses", tooltip = "The address(es) of this organization, if known. More than one " +
            "address may be specified, but the primary address must be indicated as such.")
    @Cascade(delete = true)
    private Set<Address> addresses = new HashSet<>();

    @Field(name = "organization_contacts", tooltip = "The point(s) of contact defined for this organization, if known.")
    @Cascade(delete = true)
    private Set<OrganizationContact> contacts = new HashSet<>();

    @Field(name = "organization_credentials", tooltip = "The list of credentials held by the organization. " +
            "NOTE: degree is not a valid credential type for an Organization.")
    @Cascade(delete = true)
    private Set<Credential> credentials = new HashSet<>();

    @Field(name = "organization_languages", tooltip = "The languages this organization is able to operate in, if known.")
    @Cascade(delete = true)
    private Set<CodedType> languages = new HashSet<>();

    @UIDisplayable(position = 2)
    @Field(name = "organization_specialization", tooltip = "Specialty services provided by this organization.")
    @Cascade(delete = true)
    private Set<CodedType> specializations = new HashSet<>();

    @Field(name = "organization_contact_points", tooltip = "This organization’s contact points (i.e. Business Phone, Fax, " +
            "Encryption Certificate, etc.).")
    @Cascade(delete = true)
    private Set<ContactPoint> contactPoints = new HashSet<>();

    @UIDisplayable(position = 3)
    @Field(required = true, name = "organization_coded_types", tooltip = "Organization type as identified by national or regional organizations.")
    @Cascade(delete = true)
    private Set<CodedType> codedTypes = new HashSet<>();

    @Field(tooltip = "If the organization is a suborganization, the unique IDs of the parents shall be indicated. " +
            "If it is the top level organization, its own unique ID may be used instead. If not specified, it is assumed " +
            "that the organization is top-level.")
    private String parentOrganization;

    @Ignore
    private UniqueID parent;

    @Field(name = "organization_extensions", tooltip = "This is a locally defined extension for this entity.")
    @Cascade(delete = true)
    private Set<Extension> extensions = new HashSet<>();

    public Organization() {
    }

    public Organization(String entityID, Set<CodedType> codedTypes, String primaryName, Record record) {
        setEntityID(entityID);
        this.codedTypes = codedTypes;
        this.primaryName = primaryName;
        setRecord(record);
    }

    public Organization(String entityID, Set<Credential> credentials, String parentOrganization, Set<CodedType> specializations, Set<OtherID> otherIDs, //NO CHECKSTYLE ArgumentCount
                        String primaryName, Set<OtherName> otherNames, Set<Address> addresses, Set<OrganizationContact> contacts,
                        Set<CodedType> languages, Set<ContactPoint> contactPoints, Set<CodedType> codedTypes, Set<Extension> extensions, Record record) {
        setEntityID(entityID);
        this.credentials = credentials;
        this.parentOrganization = parentOrganization;
        parent = new UniqueID(parentOrganization);
        this.specializations = specializations;
        this.otherIDs = otherIDs;
        this.primaryName = primaryName;
        this.otherNames = otherNames;
        this.addresses = addresses;
        this.contacts = contacts;
        this.languages = languages;
        this.contactPoints = contactPoints;
        this.codedTypes = codedTypes;
        this.extensions = extensions;
        setRecord(record);
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

    public Set<OtherID> getOtherIDs() {
        return otherIDs;
    }

    @XmlElement(name = "otherID")
    public void setOtherIDs(Set<OtherID> otherIDs) {
        this.otherIDs = otherIDs;
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

    public Set<Credential> getCredentials() {
        return credentials;
    }

    @XmlElement(name = "credential")
    public void setCredentials(Set<Credential> credentials) {
        this.credentials = credentials;
    }

    public Set<CodedType> getLanguages() {
        return languages;
    }

    @XmlElement(name = "language")
    public void setLanguages(Set<CodedType> languages) {
        this.languages = languages;
    }

    public Set<CodedType> getSpecializations() {
        return specializations;
    }

    @XmlElement(name = "specialization")
    public void setSpecializations(Set<CodedType> specializations) {
        this.specializations = specializations;
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

    @XmlTransient
    public String getParentOrganization() {
        return parentOrganization;
    }

    public void setParentOrganization(String parentOrganization) {
        this.parentOrganization = parentOrganization;
        parent = new UniqueID(parentOrganization);
    }

    public UniqueID getParent() {
        if (parent == null) {
            if (parentOrganization != null && !parentOrganization.isEmpty()) {
                parent = new UniqueID(parentOrganization);
            }
        }
        return parent;
    }

    @XmlElement
    public void setParent(UniqueID parent) {
        this.parent = parent;
        parentOrganization = parent.getEntityID();
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

        Organization that = (Organization) o;

        if (!primaryName.equals(that.primaryName)) {
            return false;
        }
        if (!codedTypes.equals(that.codedTypes)) {
            return false;
        }
        if (addresses != null ? !addresses.equals(that.addresses) : that.addresses != null) {
            return false;
        }
        if (otherNames != null ? !otherNames.equals(that.otherNames) : that.otherNames != null) {
            return false;
        }
        if (parentOrganization != null ? !parentOrganization.equals(that.parentOrganization) : that.parentOrganization != null) {
            return false;
        }
        if (otherIDs != null ? !otherIDs.equals(that.otherIDs) : that.otherIDs != null) {
            return false;
        }
        if (specializations != null ? !specializations.equals(that.specializations) : that.specializations != null) {
            return false;
        }
        if (contactPoints != null ? !contactPoints.equals(that.contactPoints) : that.contactPoints != null) {
            return false;
        }
        if (credentials != null ? !credentials.equals(that.credentials) : that.credentials != null) {
            return false;
        }
        if (languages != null ? !languages.equals(that.languages) : that.languages != null) {
            return false;
        }
        if (contacts != null ? !contacts.equals(that.contacts) : that.contacts != null) {
            return false;
        }
        if (extensions != null ? !extensions.equals(that.extensions) : that.extensions != null) {
            return false;
        }

        return true;
    }

    @Override //NO CHECKSTYLE CyclomaticComplexity
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (otherIDs != null ? otherIDs.hashCode() : 0);
        result = 31 * result + primaryName.hashCode();
        result = 31 * result + (otherNames != null ? otherNames.hashCode() : 0);
        result = 31 * result + (addresses != null ? addresses.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (credentials != null ? credentials.hashCode() : 0);
        result = 31 * result + (languages != null ? languages.hashCode() : 0);
        result = 31 * result + (specializations != null ? specializations.hashCode() : 0);
        result = 31 * result + (contactPoints != null ? contactPoints.hashCode() : 0);
        result = 31 * result + codedTypes.hashCode();
        result = 31 * result + (parentOrganization != null ? parentOrganization.hashCode() : 0);
        result = 31 * result + (extensions != null ? extensions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return primaryName;
    }
}
