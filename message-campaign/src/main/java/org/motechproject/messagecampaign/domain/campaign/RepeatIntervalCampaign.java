package org.motechproject.messagecampaign.domain.campaign;

import org.joda.time.Period;
import org.motechproject.messagecampaign.domain.message.CampaignMessage;
import org.motechproject.messagecampaign.exception.CampaignMessageValidationException;

import java.util.List;

/**
 * A type of a {@link Campaign}, in which all messages are repeated
 * periodically every specified amount of time.
 *
 */
public class RepeatIntervalCampaign extends Campaign {

    private Period repeatInterval;

    public RepeatIntervalCampaign() {

    }

    public RepeatIntervalCampaign(String name, List<CampaignMessage> messages) {
        this(name, messages, null);
    }

    public RepeatIntervalCampaign(String name, List<CampaignMessage> messages, Period maxDuration) {
        super(name, messages, maxDuration);
    }

    @Override
    public CampaignMessage getCampaignMessage(CampaignMessageRecord messageRecord) {
        return new CampaignMessage(messageRecord);
    }

    // TODO: deprecate in favor of getRepeatIntervalInSeconds?
    public long getRepeatIntervalInMillis(CampaignMessage cm) {
        return this.repeatInterval.getMillis();
    }

    public int getRepeatIntervalInSeconds(CampaignMessage cm) {
        return this.repeatInterval.getMillis()/1000;
    }

    @Override
    public void validate2(CampaignMessage cm) {
        if (repeatInterval == null) {
            throw new CampaignMessageValidationException("RepeatInterval cannot be null in " + RepeatIntervalCampaign.class.getName());
        } else if (getStartTime(cm) == null) {
            throw new CampaignMessageValidationException("StartTime cannot be null in " + RepeatIntervalCampaign.class.getName());
        }
    }
}
