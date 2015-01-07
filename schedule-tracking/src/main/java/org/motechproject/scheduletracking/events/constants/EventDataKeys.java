package org.motechproject.scheduletracking.events.constants;

/**
 * Keys for accessing the Event Payload
 */
public final class EventDataKeys {

    // MilestoneEvent
    public static final String WINDOW_NAME = "window_name";
    public static final String MILESTONE_NAME = "milestone_name";
    public static final String EARLIEST_DATE_TIME = "earliest_date_time";
    public static final String DUE_DATE_TIME = "due_date_time";
    public static final String LATE_DATE_TIME = "late_date_time";
    public static final String DEFAULTMENT_DATE_TIME = "defaultment_date_time";
    public static final String SCHEDULE_NAME = "schedule_name";
    public static final String EXTERNAL_ID = "external_id";
    public static final String REFERENCE_DATE = "reference_date";
    public static final String MILESTONE_DATA = "milestone_data";

    // MilestoneDefaultedEvent
    public static final String ENROLLMENT_ID = "enrollment_id";

    // Enroll and Unenroll event
    public static final String PREFERRED_ALERT_TIME = "preferred_alert_time";
    public static final String METADATA = "metadata";
    public static final String REFERENCE_TIME = "reference_time";
    public static final String ENROLLMENT_DATE = "enrollment_date";
    public static final String ENROLLMENT_TIME = "enrollment_time";

    private EventDataKeys() {
    }
}
