package org.motechproject.messagecampaign.domain.campaign;

import org.joda.time.Period;
import org.motechproject.commons.date.model.Time;
import org.motechproject.messagecampaign.domain.message.CampaignMessage;
import org.motechproject.messagecampaign.domain.message.CampaignMessageRecord;
import org.motechproject.messagecampaign.exception.CampaignValidationException;

import java.util.ArrayList;
import java.util.List;

public abstract class Campaign {
    private String name;
    private List<CampaignMessage> messages;
    private CampaignRecord campaignRecord;

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

    public void setMessageRecords(List<CampaignMessageRecord> messageRecords) {
        List<CampaignMessage> campaignMessages = new ArrayList<>();
        for (CampaignMessageRecord messageRecord : messageRecords) {
            CampaignMessage campaignMessage = getCampaignMessage(messageRecord);
            campaignMessages.add(campaignMessage);
        }
        setMessages(campaignMessages);
    }

    public abstract CampaignMessage getCampaignMessage(CampaignMessageRecord messageRecord);

    public Time getStartTime(CampaignMessage cm) {
        throw new RuntimeException("TODO: Not implemented");
    }

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
