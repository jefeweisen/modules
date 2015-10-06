package org.motechproject.commcare.pull;

/**
 * Represents the status of an ongoing form import.
 */
public class FormImportStatus {

    /**
     * The number of forms successfully imported.
     */
    private int formsImported;

    /**
     * The total number of forms to import.
     */
    private int totalForms;

    /**
     * The date of the last successfully imported form.
     */
    private String lastImportDate;

    /**
     * The ID of the last successfully imported form.
     */
    private String lastImportFormId;

    /**
     * Whether the import ended in error.
     */
    private boolean error;

    /**
     * The error message, if applicable.
     */
    private String errorMsg;

    /**
     * Is import in progress currently.
     */
    private boolean importInProgress;

    /**
     * @return the number of forms successfully imported
     */
    public int getFormsImported() {
        return formsImported;
    }

    /**
     * @param formsImported the number of forms successfully imported
     */
    public void setFormsImported(int formsImported) {
        this.formsImported = formsImported;
    }

    /**
     * @return the total number of forms to import
     */
    public int getTotalForms() {
        return totalForms;
    }

    /**
     * @param totalForms the total number of forms to import
     */
    public void setTotalForms(int totalForms) {
        this.totalForms = totalForms;
    }

    /**
     * @return the date of the last successfully imported form
     */
    public String getLastImportDate() {
        return lastImportDate;
    }

    /**
     * @param lastImportDate the date of the last successfully imported form
     */
    public void setLastImportDate(String lastImportDate) {
        this.lastImportDate = lastImportDate;
    }

    /**
     * @return true if the import ended on error, false otherwise
     */
    public boolean isError() {
        return error;
    }

    /**
     * @param error true if the import ended on error, false otherwise
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * @return the message for the error that stopped the import
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg the message for the error that stopped the import
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return true if import is in progress, false otherwise
     */
    public boolean isImportInProgress() {
        return importInProgress;
    }

    /**
     * @param importInProgress true if import is in progress, false otherwise
     */
    public void setImportInProgress(boolean importInProgress) {
        this.importInProgress = importInProgress;
    }

    /**
     * @return the ID of the last successfully imported form
     */
    public String getLastImportFormId() {
        return lastImportFormId;
    }

    /**
     * @param lastImportFormId the ID of the last successfully imported form
     */
    public void setLastImportFormId(String lastImportFormId) {
        this.lastImportFormId = lastImportFormId;
    }
}
