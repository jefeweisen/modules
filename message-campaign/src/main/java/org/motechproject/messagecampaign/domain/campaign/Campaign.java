package org.motechproject.messagecampaign.domain.campaign;

import org.joda.time.Period;
import org.motechproject.commons.date.model.Time;
import org.motechproject.messagecampaign.exception.CampaignValidationException;
import org.motechproject.messagecampaign.domain.message.CampaignMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * A base representation of a message campaign, that contains fields and methods common
 * to all campaigns. Actual campaign representations are created extending this abstract class.
 *
 * @see {@link AbsoluteCampaign}
 * @see {@link CronBasedCampaign}
 * @see {@link DayOfWeekCampaign}
 * @see {@link OffsetCampaign}
 * @see {@link RepeatIntervalCampaign}
 * @param <T> the type of messages sent during campaign; must extend base class {@link CampaignMessage}
 */
public abstract class Campaign {
    /**
     * The name of the campaign.
     */
    private String name;
    private List<CampaignMessage> messages;
    private CampaignRecord campaignRecord;

    /**
     * The list of messages to be sent during campaign.
     */

    /**
     * The maximum duration, for which the campaign will run.
     */

    public Campaign () {

    }

    public Campaign (String name, List<CampaignMessage> messages) {
        this(name, messages, null);
    }

    protected Campaign(String name, List<CampaignMessage> messages, Period maxDuration) {
        this.name = name;
        this.messages = messages;
        // TODO: this.maxDuration = maxDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CampaignMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<CampaignMessage> messages) {
        this.messages = messages;
    }

    public Period getMaxDuration() {
        return getCampaignRecord().getMaxDuration();
    }

    public void setMaxDuration(Period maxDuration) {
        getCampaignRecord().setMaxDuration(maxDuration);
    }

    /**
     * Sets message records for this campaign, from the domain representation.
     *
     * @param messageRecords a list of {@link CampaignMessageRecord}
     */
    public void setMessageRecords(List<CampaignMessageRecord> messageRecords) {
        List<CampaignMessage> campaignMessages = new ArrayList<>();
        for (CampaignMessageRecord messageRecord : messageRecords) {
            CampaignMessage campaignMessage = getCampaignMessage(messageRecord);
            campaignMessages.add(campaignMessage);
        }
        setMessages(campaignMessages);
    }

    /**
     * Converts domain representation of a campaign message to the message representation of
     * this campaign.
     *
     * @param messageRecord domain representation of the campaign message
     * @return message converted to the type supported by this campaign
     */
    public abstract CampaignMessage getCampaignMessage(CampaignMessageRecord messageRecord);

    public Time getStartTime(CampaignMessage cm) {
        throw new RuntimeException("TODO: Not implemented");
    }


    /**
     * A general validator for the created campaigns. It also triggers validation of all the
     * messages in this campaign.
     *
     * @throws CampaignValidationException if the name of the campaign is null or there are no messages assigned
     *         to this campaign.
     */
    public void validate() {
        if (name == null) {
            throw new CampaignValidationException("Name cannot be null in " + getClass().getName());
        } else if (messages == null || messages.isEmpty()) {
            throw new CampaignValidationException("Messages cannot be null or empty in " + name);
        }
        for (CampaignMessage campaignMessage : getMessages() ) {
            campaignMessage.validate();
        }
    }

    public void validate2(CampaignMessage cm) {
    }

    public CampaignRecord getCampaignRecord() {
        return campaignRecord;
    }

    public void setCampaignRecord(CampaignRecord campaignRecord) {
        this.campaignRecord = campaignRecord;
    }
}
