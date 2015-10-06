package org.motechproject.pillreminder.service;

import org.joda.time.DateTime;
import org.motechproject.commons.date.model.Time;
import org.motechproject.commons.date.util.DateUtil;
import org.motechproject.event.MotechEvent;
import org.motechproject.pillreminder.EventKeys;
import org.motechproject.pillreminder.builder.SchedulerPayloadBuilder;
import org.motechproject.pillreminder.domain.Dosage;
import org.motechproject.pillreminder.domain.PillRegimen;
import org.motechproject.scheduler.builder.CronJobSimpleExpressionBuilder;
import org.motechproject.scheduler.contract.CronSchedulableJob;
import org.motechproject.scheduler.service.MotechSchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * The scheduler facade used by the pill reminder module.
 */
@Component
public class PillRegimenJobScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PillRegimenJobScheduler.class);

    private MotechSchedulerService schedulerService;

    @Autowired
    public PillRegimenJobScheduler(MotechSchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    /**
     * Schedules a daily job for each dosage of the pill regimen. The jobs will fire events
     * that will be handled by the pill reminder module.
     * @param pillRegimen the regimen for which reminders should get scheduled
     */
    public void scheduleDailyJob(PillRegimen pillRegimen) {
        for (Dosage dosage : pillRegimen.getDosages()) {
            CronSchedulableJob schedulableJob = getSchedulableDailyJob(pillRegimen, dosage);
            schedulerService.safeScheduleJob(schedulableJob);
        }
    }

    /**
     * Unschedules all jobs scheduled for dosages from the given pill regimen.
     * @param regimen the regimen to unschedule the jobs for
     */
    public void unscheduleJobs(PillRegimen regimen) {
        for (Dosage dosage : regimen.getDosages()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Unscheduling jobs for dosage with id {}", dosage.getId());
            }

            schedulerService.safeUnscheduleJob(EventKeys.PILLREMINDER_REMINDER_EVENT_SUBJECT_SCHEDULER,
                    dosage.getId().toString());
            schedulerService.safeUnscheduleRepeatingJob(EventKeys.PILLREMINDER_REMINDER_EVENT_SUBJECT_SCHEDULER,
                    dosage.getId().toString());
        }
    }

    protected CronSchedulableJob getSchedulableDailyJob(PillRegimen pillRegimen, Dosage dosage) {
        Map<String, Object> eventParams = new SchedulerPayloadBuilder()
                .withJobId(dosage.getId().toString())
                .withDosageId(dosage.getId())
                .withExternalId(pillRegimen.getExternalId())
                .payload();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Scheduling reminder job: JobId='%s', DosageId='%d', ExternalId='%s'",
                    dosage.getId().toString(), dosage.getId(), pillRegimen.getExternalId()));
        }

        final Time dosageTime = dosage.getDosageTime();
        DateTime cronStartDateTime = DateUtil.newDateTime(dosage.getStartDate(), dosageTime.getHour(), dosageTime.getMinute(), 0);
        DateTime adjustedCronStartDateTime = cronStartDateTime.plusMinutes(pillRegimen.getScheduleDetails().getBufferOverDosageTimeInMinutes());

        MotechEvent motechEvent = new MotechEvent(EventKeys.PILLREMINDER_REMINDER_EVENT_SUBJECT_SCHEDULER, eventParams);
        String cronJobExpression = new CronJobSimpleExpressionBuilder(new Time(adjustedCronStartDateTime.toLocalTime())).build();
        Date endDate = dosage.getEndDate() == null ? null : dosage.getEndDate().toDate();
        Date startDate = DateUtil.newDateTime(adjustedCronStartDateTime.toDate()).isBefore(DateUtil.now()) ? DateUtil.now().toDate() : adjustedCronStartDateTime.toDate();
        return new CronSchedulableJob(motechEvent, cronJobExpression, startDate, endDate);
    }
}
