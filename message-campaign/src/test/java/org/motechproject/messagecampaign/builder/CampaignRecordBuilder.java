package org.motechproject.messagecampaign.builder;

import org.joda.time.Period;
import org.motechproject.messagecampaign.domain.campaign.CampaignRecord;
import org.motechproject.messagecampaign.domain.campaign.CampaignType;
import org.motechproject.messagecampaign.domain.campaign.CampaignMessageRecord;

import java.util.ArrayList;
import java.util.List;

public final class CampaignRecordBuilder {

    public static CampaignRecord absoluteCampaignRecord(String name, CampaignMessageRecord absoluteCampaignMessageRecord) {
        List<CampaignMessageRecord> campaignMessageRecords = new ArrayList<CampaignMessageRecord>();
        campaignMessageRecords.add(absoluteCampaignMessageRecord);

        CampaignRecord record = new CampaignRecord();
        record.setName(name);
        record.setCampaignType(CampaignType.ABSOLUTE);
        record.setMessages(campaignMessageRecords);

        return record;
    }

    public static CampaignRecord offsetCampaignRecord(String name, CampaignMessageRecord offsetCampaignMessageRecord) {
        List<CampaignMessageRecord> campaignMessageRecords = new ArrayList<CampaignMessageRecord>();
        campaignMessageRecords.add(offsetCampaignMessageRecord);

        CampaignRecord record = new CampaignRecord();
        record.setName(name);
        record.setCampaignType(CampaignType.OFFSET);
        record.setMaxDuration(new Period("2 Weeks"));
        record.setMessages(campaignMessageRecords);

        return record;
    }

    public static CampaignRecord cronBasedCampaignRecord(String name, CampaignMessageRecord cronBasedMessageRecord) {
        List<CampaignMessageRecord> campaignMessageRecords = new ArrayList<CampaignMessageRecord>();
        campaignMessageRecords.add(cronBasedMessageRecord);

        CampaignRecord record = new CampaignRecord();
        record.setName(name);
        record.setCampaignType(CampaignType.CRON);
        record.setMessages(campaignMessageRecords);

        return record;
    }
}
