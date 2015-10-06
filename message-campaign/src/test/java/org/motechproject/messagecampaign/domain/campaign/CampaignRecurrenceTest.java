package org.motechproject.messagecampaign.domain.campaign;

import org.junit.Test;
import org.motechproject.commons.date.util.JodaFormatter;
import org.motechproject.messagecampaign.builder.CampaignMessageRecordTestBuilder;
import org.motechproject.messagecampaign.builder.CampaignRecordBuilder;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class CampaignRecurrenceTest {

    private CampaignRecord campaignRecord;
    private CampaignMessageRecord messageRecord;

    @Test
    public void testBuildAbsoluteCampaign() {
        messageRecord = CampaignMessageRecordTestBuilder.createAbsoluteCampaignMessageRecord("Message 1", "message-key");
        campaignRecord = CampaignRecordBuilder.absoluteCampaignRecord("Campaign 1", messageRecord);

        Campaign campaign = campaignRecord.toCampaign();
        assertTrue(campaign instanceof AbsoluteCampaign);
        AbsoluteCampaign absoluteCampaign = (AbsoluteCampaign) campaign;
        assertEquals(campaignRecord.getName(), absoluteCampaign.getName());
        List<CampaignMessage> messages = absoluteCampaign.getMessages();
        assertEquals(1, messages.size());

        CampaignMessage message = messages.get(0);
        assertEquals(messageRecord.getName(), message.getName());
        assertEquals(messageRecord.getFormats(), message.getFormats());
        assertEquals(messageRecord.getLanguages(), message.getLanguages());
        assertEquals(messageRecord.getMessageKey(), message.getMessageKey());
        assertEquals(messageRecord.getDate(), absoluteCampaign.getDate(message));
    }

    @Test
    public void testBuildOffsetCampaign() {
        messageRecord = CampaignMessageRecordTestBuilder.createOffsetCampaignMessageRecord("Message 1", "message-key");
        campaignRecord = CampaignRecordBuilder.offsetCampaignRecord("Campaign 1", messageRecord);

        Campaign campaign = campaignRecord.toCampaign();
        assertTrue(campaign instanceof OffsetCampaign);
        OffsetCampaign offsetCampaign = (OffsetCampaign) campaign;
        assertEquals(campaignRecord.getName(), offsetCampaign.getName());
        assertEquals(campaignRecord.getMaxDuration(), offsetCampaign.getMaxDuration());
        List<CampaignMessage> messages = offsetCampaign.getMessages();
        assertEquals(1, messages.size());

        CampaignMessage message = messages.get(0);
        assertEquals(messageRecord.getName(), message.getName());
        assertEquals(messageRecord.getFormats(), message.getFormats());
        assertEquals(messageRecord.getLanguages(), message.getLanguages());
        assertEquals(messageRecord.getMessageKey(), message.getMessageKey());
        assertEquals(new JodaFormatter().parsePeriod(messageRecord.getTimeOffset()), offsetCampaign.getTimeOffset());

    }

    @Test
    public void testBuildCronBasedCampaign() {
        messageRecord = CampaignMessageRecordTestBuilder.createCronBasedCampaignMessageRecord("Message 1", "message-key");
        campaignRecord = CampaignRecordBuilder.cronBasedCampaignRecord("Campaign 1", messageRecord);

        Campaign campaign = campaignRecord.toCampaign();
        assertTrue(campaign instanceof CronBasedCampaign);
        CronBasedCampaign cronBasedCampaign = (CronBasedCampaign) campaign;
        assertEquals(campaignRecord.getName(), cronBasedCampaign.getName());
        List<CampaignMessage> messages = cronBasedCampaign.getMessages();
        assertEquals(1, messages.size());

        CampaignMessage message = messages.get(0);
        assertEquals(messageRecord.getName(), message.getName());
        assertEquals(messageRecord.getFormats(), message.getFormats());
        assertEquals(messageRecord.getLanguages(), message.getLanguages());
        assertEquals(messageRecord.getMessageKey(), message.getMessageKey());
        assertEquals(messageRecord.getCron(), cronBasedCampaign.getCron(message));
    }
}
