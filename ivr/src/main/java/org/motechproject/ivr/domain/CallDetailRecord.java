package org.motechproject.ivr.domain;

import org.apache.commons.collections.MapUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.motechproject.commons.date.util.DateUtil;
import org.motechproject.ivr.util.Constants;
import org.motechproject.mds.annotations.Access;
import org.motechproject.mds.annotations.CrudEvents;
import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;
import org.motechproject.mds.annotations.Ignore;
import org.motechproject.mds.annotations.UIDisplayable;
import org.motechproject.mds.event.CrudEventType;
import org.motechproject.mds.util.SecurityMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The call detail record is the object persisted for every call from and to MOTECH by the IVR module.
 * It represents the details of the call, and is stored in the database for auditing purposes.
 */
@Entity(nonEditable = true)
@CrudEvents(CrudEventType.NONE)
@Access(value = SecurityMode.PERMISSIONS, members = {Constants.VIEW_IVR_LOGS_PERMISSION})
public class CallDetailRecord {

    private static final int COL1 = 0;
    private static final int COL2 = 1;
    private static final int COL3 = 2;
    private static final int COL4 = 3;
    private static final int COL5 = 4;
    private static final int COL6 = 5;
    private static final int COL7 = 6;
    private static final int COL8 = 7;
    private static final int COL9 = 8;
    private static final int COL10 = 9;
    private static final int COL11 = 10;
    private static final int COL12 = 11;
    private static final int COL13 = 12;
    private static final int COL14 = 13;
    private static final int MAX_ENTITY_STRING_LENGTH = 255;
    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSS");
    private static final Logger LOGGER = LoggerFactory.getLogger(CallDetailRecord.class);

    public static final String CALL_FAILED = "FAILED";
    public static final String CALL_INITIATED = "INITIATED";

    @Field
    @UIDisplayable(position = COL1)
    private long id;

    @Field(required = true)
    @UIDisplayable(position = COL11)
    private String motechTimestamp;

    @Field
    @UIDisplayable(position = COL12)
    private String providerTimestamp;

    @Field(required = true)
    @UIDisplayable(position = COL2)
    private String configName;

    @Field
    @UIDisplayable(position = COL3)
    private String from;

    @Field
    @UIDisplayable(position = COL4)
    private String to;

    @Field
    @UIDisplayable(position = COL5)
    private CallDirection callDirection;

    @Field
    @UIDisplayable(position = COL6)
    private String callStatus;

    @Field
    @UIDisplayable(position = COL7)
    private String templateName;

    @Field
    @UIDisplayable(position = COL9)
    private String motechCallId;

    @Field
    @UIDisplayable(position = COL10)
    private String providerCallId;

    @Field
    @UIDisplayable(position = COL8)
    private Map<String, String> providerExtraData;

    @Field
    @UIDisplayable(position = COL13)
    private String callDuration;

    @Field
    @UIDisplayable(position = COL14)
    private String messagePercentListened;

    /**
     * Creates a new CallDetailRecord and sets the motechTimestamp value to the current datetime.
     */
    public CallDetailRecord() {
        providerExtraData = new HashMap<>();
        this.motechTimestamp = DT_FORMATTER.print(DateUtil.now());
    }

    /**
     * Creates a new CallDetailRecord and sets the motechTimestamp value to the current datetime.
     * @param configName the name of the IVR configuration used for the call this record corresponds to
     * @param providerTimestamp the timestamp for this call from the IVR provider
     * @param from the initiator of this call
     * @param to the recipient of this call
     * @param callDirection the direction of this call
     * @param callStatus the status of this call
     * @param templateName the name of the template associated with this call
     * @param motechCallId the id for this call generated by MOTECH
     * @param providerCallId the id for this call generated by the IVR provider
     * @param providerExtraData the extra data coming from the provider
     */
    public CallDetailRecord(String configName,  //NO CHECKSTYLE ParameterNumber
                            String providerTimestamp, String from, String to, CallDirection callDirection,
                            String callStatus, String templateName, String motechCallId, String providerCallId,
                            Map<String, String> providerExtraData, String callDuration, String messagePercentListened) {
        this();
        this.configName = configName;
        this.providerTimestamp = providerTimestamp;
        this.from = from;
        this.to = to;
        this.callDirection = callDirection;
        this.callStatus = callStatus;
        this.templateName = templateName;
        this.motechCallId = motechCallId;
        this.providerCallId = providerCallId;
        if (providerExtraData != null) {
            this.providerExtraData = providerExtraData;
        }
        this.callDuration = callDuration;
        this.messagePercentListened = messagePercentListened;
    }

    /**
     * @return the id of the record (auto-generated)
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the current datetime as a string formatted using the datetime formatter used for timestamps
     * in call detail records. The format is <code>yyyy-MM-dd HH:mm:ss.SSSS</code>.
     * @return the current time in the call detail record timestamp format
     */
    @Ignore
    public static String getCurrentTimestamp() {
        return DT_FORMATTER.print(DateUtil.now());
    }

    /**
     * @return the name of the IVR configuration used for the call this record corresponds to
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * @param configName the name of the IVR configuration used for the call this record corresponds to
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * Returns the timestamp for this record. The format is <code>yyyy-MM-dd HH:mm:ss.SSSS</code>.
     * @return the timestamp for this record
     */
    public String getMotechTimestamp() {
        return motechTimestamp;
    }

    /**
     * Sets the timestamp for this record. The expected format is <code>yyyy-MM-dd HH:mm:ss.SSSS</code>.
     * @param motechTimestamp the timestamp for this record
     */
    public void setMotechTimestamp(String motechTimestamp) {
        this.motechTimestamp = motechTimestamp;
    }

    /**
     * @return the timestamp for this call from the IVR provider
     */
    public String getProviderTimestamp() {
        return providerTimestamp;
    }

    /**
     * @param providerTimestamp the timestamp for this call from the IVR provider
     */
    public void setProviderTimestamp(String providerTimestamp) {
        this.providerTimestamp = providerTimestamp;
    }

    /**
     * Returns the initiator of the call represented by this record. This usually will be the number of the phone that initiated the call.
     * @return the initiator of the call
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the initiator of the call represented by this record. This usually will be the phone number that initiated the call.
     * @param from the initiator of the call
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Returns the recipient of the call represented by this record. This usually will the phone number that was called.
     * @return the recipient of this call
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the recipient of the call represented by this record. This usually will the phone number that was called.
     * @param to the recipient of this call
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the direction of this call
     * @see org.motechproject.ivr.domain.CallDirection
     */
    public CallDirection getCallDirection() {
        return callDirection;
    }

    /**
     * Returns the status of this call, which is a string coming from the IVR provider.
     * @return the status of this call
     */
    public String getCallStatus() {
        return callStatus;
    }

    /**
     * @return the name of the template associated with this call
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the name of the template associated with this call
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * Returns the ID generated on the MOTECH side for this call. Motech will insert an UUID here.
     * @return the call ID generated by MOTECH
     */
    public String getMotechCallId() {
        return motechCallId;
    }

    /**
     * @return the ID for this call coming from the provider
     */
    public String getProviderCallId() {
        return providerCallId;
    }

    /**
     * Returns the extra data for this call detail record. Everything that comes from the provider and does
     * not match any mapping to the fields of CallDetailRecord, will be put into the the provider extra data map.
     * @return the extra data from the provider
     */
    public Map<String, String> getProviderExtraData() {
        return providerExtraData;
    }

    /**
     * @return the duration of this call
     */
    public String getCallDuration() {
        return callDuration;
    }

    /**
     * @return the percent listened of the message
     */
    public String getMessagePercentListened() {
        return messagePercentListened;
    }

    /**
     * When receiving call detail information from an IVR provider the specific call details must be mapped from
     * what the provider sends back to MOTECH and a CallDetailRecord object. This method will find which field on the
     * given callDetailRecord matches the given key and set service to the given value. If there is no matching
     * CallDetailRecord field, then the key/value pair is added to the providerExtraData map field.
     *
     * @param key the name of the field to set
     * @param val the value to set
     * @param callStatusMapping the map which contains mapping for call status. It is used to change status value in the cdr log.
     */
    public void setField(String key, String val, Map<String, String> callStatusMapping) {
        String value;

        if (val.length() > MAX_ENTITY_STRING_LENGTH) {
            LOGGER.warn("The value for {} exceeds {} characters and will be truncated.", key, MAX_ENTITY_STRING_LENGTH);
            LOGGER.warn("The complete value for {} is {}", key, val);
            value = val.substring(0, MAX_ENTITY_STRING_LENGTH);
        }  else {
            value = val;
        }

        try {
            java.lang.reflect.Field field = this.getClass().getDeclaredField(key);
            Object object;
            try {
                switch (key) {
                    case "callDirection":
                        try {
                            object = CallDirection.valueOf(value);
                        } catch (IllegalArgumentException e) {
                            // Always add unknown call directions to the provider extra data, for inspection
                            LOGGER.warn("Unknown callDirection: {}", value);
                            providerExtraData.put(key, value);
                            object = CallDirection.UNKNOWN;
                        }
                        break;
                    case "callStatus":
                        Object statusValue = value;
                        if (MapUtils.isNotEmpty(callStatusMapping)) {
                            statusValue = callStatusMapping.get(value.toString()) == null ? value : callStatusMapping.get(value.toString());
                        }
                        object = statusValue;
                        break;
                    default:
                        object = value;
                        break;
                }
                field.set(this, object);
            } catch (IllegalAccessException e) {
                // This should never happen as all CallDetailRecord fields should be accessible, but if somehow there
                // happens to be a 'final' public field with the same name as a call detail key, then this will throw
                throw new IllegalStateException(String.format(
                        "Unable to set call detail record field '%s' to value '%s':\n%s", key, value, e));
            }
        } catch (NoSuchFieldException e) {
            LOGGER.info("Extra data from provider: '{}': '{}'", key, value);
            providerExtraData.put(key, value);
        }
    }

    @Override //NO CHECKSTYLE CyclomaticComplexity
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CallDetailRecord)) {
            return false;
        }

        CallDetailRecord that = (CallDetailRecord) o;

        if (id != that.id) {
            return false;
        }
        if (callDirection != that.callDirection) {
            return false;
        }
        if (!Objects.equals(callStatus, that.callStatus)) {
            return false;
        }
        if (!configName.equals(that.configName)) {
            return false;
        }
        if (from != null ? !from.equals(that.from) : that.from != null) {
            return false;
        }
        if (motechCallId != null ? !motechCallId.equals(that.motechCallId) : that.motechCallId != null) {
            return false;
        }
        if (motechTimestamp != null ? !motechTimestamp.equals(that.motechTimestamp) : that.motechTimestamp != null) {
            return false;
        }
        if (providerCallId != null ? !providerCallId.equals(that.providerCallId) : that.providerCallId != null) {
            return false;
        }
        if (providerExtraData != null ? !providerExtraData.equals(that.providerExtraData) : that.providerExtraData !=
                null) {
            return false;
        }
        if (providerTimestamp != null ? !providerTimestamp.equals(that.providerTimestamp) : that.providerTimestamp !=
                null) {
            return false;
        }
        if (templateName != null ? !templateName.equals(that.templateName) : that.templateName != null) {
            return false;
        }
        if (to != null ? !to.equals(that.to) : that.to != null) {
            return false;
        }
        if (callDuration != null ? !callDuration.equals(that.callDuration) : that.callDuration != null) {
            return false;
        }
        if (messagePercentListened != null ? !messagePercentListened.equals(that.messagePercentListened) : that.messagePercentListened != null) {
            return false;
        }

        return true;
    }

    @Override //NO CHECKSTYLE CyclomaticComplexity
    public int hashCode() {
        int result = motechTimestamp != null ? motechTimestamp.hashCode() : 0;
        result = 31 * result + (providerTimestamp != null ? providerTimestamp.hashCode() : 0);
        result = 31 * result + (configName != null ? configName.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (callDirection != null ? callDirection.hashCode() : 0);
        result = 31 * result + (callStatus != null ? callStatus.hashCode() : 0);
        result = 31 * result + (templateName != null ? templateName.hashCode() : 0);
        result = 31 * result + (motechCallId != null ? motechCallId.hashCode() : 0);
        result = 31 * result + (providerCallId != null ? providerCallId.hashCode() : 0);
        result = 31 * result + (providerExtraData != null ? providerExtraData.hashCode() : 0);
        result = 31 * result + (callDuration != null ? callDuration.hashCode() : 0);
        result = 31 * result + (messagePercentListened != null ? messagePercentListened.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CallDetailRecord{" +
                "motechTimestamp='" + motechTimestamp + '\'' +
                ", providerTimestamp='" + providerTimestamp + '\'' +
                ", configName='" + configName + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", callDirection=" + callDirection +
                ", callStatus=" + callStatus +
                ", templateName=" + templateName +
                ", motechCallId='" + motechCallId + '\'' +
                ", providerCallId='" + providerCallId + '\'' +
                ", providerExtraData=" + providerExtraData +
                ", callDuration=" + callDuration +
                ", messagePercentListened=" + messagePercentListened +
                '}';
    }
}
