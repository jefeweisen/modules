package org.motechproject.messagecampaign.domain.campaign;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

@Entity
public class CronBasedCampaignRecord extends CampaignType2 {
    @Field
    private String cron;

}
