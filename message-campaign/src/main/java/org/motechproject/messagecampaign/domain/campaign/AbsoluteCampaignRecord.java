package org.motechproject.messagecampaign.domain.campaign;

import org.joda.time.LocalDate;
import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

@Entity
public class AbsoluteCampaignRecord extends CampaignType2 {
    @Field
    private LocalDate date;
}
