package org.motechproject.openmrs19.helper;

import org.motechproject.openmrs19.service.EventKeys;
import org.motechproject.openmrs19.domain.OpenMRSConcept;
import org.motechproject.openmrs19.domain.OpenMRSEncounter;
import org.motechproject.openmrs19.domain.OpenMRSFacility;
import org.motechproject.openmrs19.domain.OpenMRSObservation;
import org.motechproject.openmrs19.domain.OpenMRSPatient;
import org.motechproject.openmrs19.domain.OpenMRSPerson;
import org.motechproject.openmrs19.domain.OpenMRSProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for parsing various classes to a map of parameters that can be attached to a
 * {@link org.motechproject.event.MotechEvent} and sent via the {@link org.motechproject.event.listener.EventRelay}.
 */
public final class EventHelper {

    /**
     * Utility class, should not be instantiated.
     */
    private EventHelper() {
    }

    /**
     * Parses the given {@link OpenMRSPatient} to a map of parameters, which can then be attached to a
     * {@link org.motechproject.event.MotechEvent} and sent via the {@link org.motechproject.event.listener.EventRelay}.
     *
     * @param patient  the patient to be parsed
     * @return the map of the parameters
     */
    public static Map<String, Object> patientParameters(OpenMRSPatient patient) {
        Map<String, Object> patientParameters = new HashMap<>();
        patientParameters.put(EventKeys.PATIENT_ID, patient.getPatientId());
        if (patient.getFacility() != null) {
            patientParameters.put(EventKeys.FACILITY_ID, patient.getFacility().getFacilityId());
        } else {
            patientParameters.put(EventKeys.FACILITY_ID, null);
        }
        patientParameters.put(EventKeys.MOTECH_ID, patient.getMotechId());
        if (patient.getPerson() != null) {
            patientParameters.put(EventKeys.PERSON_ID, patient.getPerson().getPersonId());
        } else {
            patientParameters.put(EventKeys.PERSON_ID, null);
        }
        return patientParameters;
    }

    /**
     * Parses the given {@link OpenMRSPerson} to a map of parameters, which can then be attached to a
     * {@link org.motechproject.event.MotechEvent} and sent via the {@link org.motechproject.event.listener.EventRelay}.
     *
     * @param person  the person to be parsed
     * @return the map of the parameters
     */
    public static Map<String, Object> personParameters(OpenMRSPerson person) {
        Map<String, Object> personParameters = new HashMap<>();
        personParameters.put(EventKeys.PERSON_ID, person.getPersonId());
        personParameters.put(EventKeys.PERSON_FIRST_NAME, person.getFirstName());
        personParameters.put(EventKeys.PERSON_MIDDLE_NAME, person.getMiddleName());
        personParameters.put(EventKeys.PERSON_LAST_NAME, person.getLastName());
        personParameters.put(EventKeys.PERSON_PREFERRED_NAME, person.getPreferredName());
        personParameters.put(EventKeys.PERSON_ADDRESS, person.getAddress());
        personParameters.put(EventKeys.PERSON_DATE_OF_BIRTH, person.getDateOfBirth());
        personParameters.put(EventKeys.PERSON_BIRTH_DATE_ESTIMATED, person.getBirthDateEstimated());
        personParameters.put(EventKeys.PERSON_AGE, person.getAge());
        personParameters.put(EventKeys.PERSON_GENDER, person.getGender());
        personParameters.put(EventKeys.PERSON_DEAD, person.isDead());
        personParameters.put(EventKeys.PERSON_DEATH_DATE, person.getDeathDate());
        return personParameters;
    }

    /**
     * Parses the given {@link OpenMRSEncounter} to a map of parameters, which can then be attached to a
     * {@link org.motechproject.event.MotechEvent} and sent via the {@link org.motechproject.event.listener.EventRelay}.
     *
     * @param encounter  the encounter to be parsed
     * @return the map of the parameters
     */
    public static Map<String, Object> encounterParameters(OpenMRSEncounter encounter) {
        Map<String, Object> encounterParameters = new HashMap<>();
        encounterParameters.put(EventKeys.ENCOUNTER_ID, encounter.getEncounterId());
        if (encounter.getProvider() != null) {
            encounterParameters.put(EventKeys.PROVIDER_ID, encounter.getProvider().getProviderId());
        } else {
            encounterParameters.put(EventKeys.PROVIDER_ID, null);
        }
        if (encounter.getCreator() != null) {
            encounterParameters.put(EventKeys.USER_ID, encounter.getCreator().getUserId());
        } else {
            encounterParameters.put(EventKeys.USER_ID, null);
        }
        if (encounter.getFacility() != null) {
            encounterParameters.put(EventKeys.FACILITY_ID, encounter.getFacility().getFacilityId());
        } else {
            encounterParameters.put(EventKeys.FACILITY_ID, null);
        }
        encounterParameters.put(EventKeys.ENCOUNTER_DATE, encounter.getDate());
        if (encounter.getObservations() != null && !encounter.getObservations().isEmpty()) {
            OpenMRSObservation obs = encounter.getObservations().iterator().next();
            encounterParameters.put(EventKeys.OBSERVATION_DATE, obs.getDate());
            encounterParameters.put(EventKeys.OBSERVATION_CONCEPT_NAME, obs.getConceptName());
            encounterParameters.put(EventKeys.PATIENT_ID, obs.getPatientId());
            encounterParameters.put(EventKeys.OBSERVATION_VALUE, obs.getValue());
        } else {
            encounterParameters.put(EventKeys.OBSERVATION_DATE, null);
            encounterParameters.put(EventKeys.OBSERVATION_CONCEPT_NAME, null);
            encounterParameters.put(EventKeys.PATIENT_ID, null);
            encounterParameters.put(EventKeys.OBSERVATION_VALUE, null);
        }

        encounterParameters.put(EventKeys.ENCOUNTER_TYPE, encounter.getEncounterType());
        return encounterParameters;
    }

    /**
     * Parses the given ID of the encounter to a map with a single parameter, which can then be attached to a
     * {@link org.motechproject.event.MotechEvent} and sent via the {@link org.motechproject.event.listener.EventRelay}.
     *
     * @param uuid  the encounter ID to be parsed
     * @return the map with single ID parameter
     */
    public static Map<String, Object> encounterParameters(String uuid) {
        Map<String, Object> encounterParameters = new HashMap<>();
        encounterParameters.put(EventKeys.ENCOUNTER_ID, uuid);
        return encounterParameters;
    }

    /**
     * Parses the given {@link OpenMRSObservation} to a map of parameters, which can then be attached to a
     * {@link org.motechproject.event.MotechEvent} and sent via the {@link org.motechproject.event.listener.EventRelay}.
     *
     * @param obs  the observation to be parsed
     * @return the map of the parameters
     */
    public static Map<String, Object> observationParameters(OpenMRSObservation obs) {
        Map<String, Object> observationParameters = new HashMap<>();
        observationParameters.put(EventKeys.OBSERVATION_DATE, obs.getDate());
        observationParameters.put(EventKeys.OBSERVATION_CONCEPT_NAME, obs.getConceptName());
        observationParameters.put(EventKeys.PATIENT_ID, obs.getPatientId());
        observationParameters.put(EventKeys.OBSERVATION_VALUE, obs.getValue());
        return  observationParameters;
    }

    /**
     * Parses the given {@link OpenMRSFacility} to a map of parameters, which can then be attached to a
     * {@link org.motechproject.event.MotechEvent} and sent via the {@link org.motechproject.event.listener.EventRelay}.
     *
     * @param facility  the facility to be parsed
     * @return the map of the parameters
     */
    public static Map<String, Object> facilityParameters(OpenMRSFacility facility) {
        Map<String, Object> facilityParameters = new HashMap<>();
        facilityParameters.put(EventKeys.FACILITY_ID, facility.getFacilityId());
        facilityParameters.put(EventKeys.FACILITY_NAME, facility.getName());
        facilityParameters.put(EventKeys.FACILITY_COUNTRY, facility.getCountry());
        facilityParameters.put(EventKeys.FACILITY_REGION, facility.getRegion());
        facilityParameters.put(EventKeys.FACILITY_COUNTY_DISTRICT, facility.getCountyDistrict());
        facilityParameters.put(EventKeys.FACILITY_STATE_PROVINCE, facility.getStateProvince());
        return facilityParameters;
    }

    /**
     * Parses the given {@link OpenMRSProvider} to a map of parameters, which can then be attached to a
     * {@link org.motechproject.event.MotechEvent} and sent via the {@link org.motechproject.event.listener.EventRelay}.
     *
     * @param provider  the provider to be parsed
     * @return the map of the parameters
     */
    public static Map<String, Object> providerParameters(OpenMRSProvider provider) {
        Map<String, Object> providerParameters = new HashMap<>();
        providerParameters.put(EventKeys.PROVIDER_ID, provider.getProviderId());
        if (provider.getPerson() != null) {
            providerParameters.put(EventKeys.PERSON_ID, provider.getPerson().getPersonId());
        } else {
            providerParameters.put(EventKeys.PERSON_ID, null);
        }
        return providerParameters;
    }

    /**
     * Parses the given {@link OpenMRSConcept} to a map of parameters, which can then be attached to a
     * {@link org.motechproject.event.MotechEvent} and sent via the {@link org.motechproject.event.listener.EventRelay}.
     *
     * @param concept  the concept to be parsed
     * @return the map of the parameters
     */
    public static Map<String, Object> conceptParameters(OpenMRSConcept concept) {
        Map<String, Object> conceptParameters = new HashMap<>();
        conceptParameters.put(EventKeys.CONCEPT_NAME, concept.getDisplay());
        conceptParameters.put(EventKeys.CONCEPT_UUID, concept.getUuid());
        conceptParameters.put(EventKeys.CONCEPT_DATA_TYPE, concept.getDataType());
        conceptParameters.put(EventKeys.CONCEPT_CONCEPT_CLASS, concept.getConceptClass());
        return conceptParameters;
    }
}
