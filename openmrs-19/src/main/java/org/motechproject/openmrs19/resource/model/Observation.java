package org.motechproject.openmrs19.resource.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Represents a single observation. An observation is a single piece of information that is recorded about a patient at
 * a moment in time.
 */
public class Observation {

    private String uuid;
    private Concept concept;
    private Encounter encounter;
    private ObservationValue value;
    private Date obsDatetime;
    private Person person;
    private List<Observation> groupsMembers;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public ObservationValue getValue() {
        return value;
    }

    public void setValue(ObservationValue value) {
        this.value = value;
    }

    public Date getObsDatetime() {
        return obsDatetime;
    }

    public void setObsDatetime(Date obsDatetime) {
        this.obsDatetime = obsDatetime;
    }

    public List<Observation> getGroupsMembers() {
        return groupsMembers;
    }

    public void setGroupsMembers(List<Observation> groupsMembers) {
        this.groupsMembers = groupsMembers;
    }

    public boolean hasConceptByName(String conceptName) {
        return concept.getDisplay().equals(conceptName);
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    /**
     * Implementation of the {@link JsonSerializer} interface for the {@link Observation} class.
     */
    public static class ObservationSerializer implements JsonSerializer<Observation> {

        @Override
        public JsonElement serialize(Observation observation, Type type, JsonSerializationContext jsonSerializationContext) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

            JsonObject object = new JsonObject();
            if (observation.uuid != null) {
                object.addProperty("uuid", observation.uuid);
            }
            if (observation.concept != null) {
                object.addProperty("concept", observation.concept.getUuid());
            }
            if (observation.encounter != null) {
                object.addProperty("encounter", observation.encounter.getUuid());
            }
            if (observation.value != null) {
                object.addProperty("value", observation.value.getDisplay());
            }
            if (observation.obsDatetime != null) {
                object.addProperty("obsDatetime", sdf.format(observation.obsDatetime));
            }
            if (observation.person != null) {
                object.addProperty("person", observation.person.getUuid());
            }

            return object;
        }
    }

    /**
     * Represents a single value of the observation.
     */
    public static class ObservationValue {
        private String display;

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }
    }

    /**
     * Implementation of the {@link JsonSerializer} interface for the
     * {@link org.motechproject.openmrs19.resource.model.Observation.ObservationValue} class.
     */
    public static class ObservationValueSerializer implements JsonSerializer<ObservationValue> {
        @Override
        public JsonElement serialize(ObservationValue src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getDisplay());
        }
    }

    /**
     * Implementation of the {@link JsonDeserializer} interface for the
     * {@link org.motechproject.openmrs19.resource.model.Observation.ObservationValue} class.
     */
    public static class ObservationValueDeserializer implements JsonDeserializer<ObservationValue> {
        @Override
        public ObservationValue deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            if (json.isJsonPrimitive()) {
                ObservationValue value = new ObservationValue();
                value.setDisplay(json.getAsString());
                return value;
            } else {
                ObservationValue value = new ObservationValue();
                value.setDisplay(json.getAsJsonObject().get("display").getAsString());
                return value;
            }
        }
    }
}
