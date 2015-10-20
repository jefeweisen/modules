package org.motechproject.messagecampaign.domain.campaign;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

import java.util.List;

@Entity
public class DayOfWeekCampaignRecord extends CampaignType2 {
    @Field
    private List<DayOfWeek> daysOfWeek;
}
