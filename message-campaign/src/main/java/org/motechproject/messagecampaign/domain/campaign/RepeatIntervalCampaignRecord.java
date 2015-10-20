package org.motechproject.messagecampaign.domain.campaign;

import org.joda.time.Period;
import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

@Entity
public class RepeatIntervalCampaignRecord extends CampaignType2 {
    @Field
    private Period repeatInterval;
}
