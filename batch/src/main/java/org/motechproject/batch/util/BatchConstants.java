package org.motechproject.batch.util;

/**
 * Constants used in the Batch module.
 */
public final class BatchConstants {

    public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String EVENT_SUBJECT = "BATCH_JOB_TRIGGERED";
    public static final String PARAMS_KEY = "Params";
    public static final String JOB_NAME_KEY = "Job_Name";
    public static final int SHORT_CONTEXT_LENGTH = 2500;
    public static final int CREATED_BY_LENGTH = 15;
    public static final int JOB_NAME_LENGTH = 100;
    public static final int JOB_KEY_LENGTH = 32;
    public static final int LAST_UPDATED_BY_LENGTH = 15;
    public static final int STEP_NAME_LENGTH = 100;
    public static final int START_TIME_LENGTH = 29;
    public static final int END_TIME_LENGTH = 29;
    public static final int STATUS_LENGTH = 10;
    public static final int EXIT_CODE_LENGTH = 2500;
    public static final int EXIT_MESSAGE_LENGTH = 29;
    public static final int LAST_UPDATED_LENGTH = 29;
    public static final int CREATE_TIME_LENGTH = 29;
    public static final int JOB_CONFIGURATION_LOCATION_LENGTH = 2500;
    public static final int TYPE_CD_LENGTH = 6;
    public static final int KEY_NAME_LENGTH = 100;
    public static final int STRING_VAL_LENGTH = 250;
    public static final int DATE_VAL_LENGTH = 29;
    public static final int DOUBLE_VAL_PRECISION = 17;
    public static final int DOUBLE_VAL_SCALE = 17;
    public static final int CODE_LENGTH = 1000;
    public static final String JOB_NAME_REQUEST_PARAM = "jobName";
    public static final String BATCH_XML_CONFIG_PATH = "META-INF/batch-jobs/";
    public static final String XML_EXTENSION = ".xml";

    private BatchConstants() {
    }
}
