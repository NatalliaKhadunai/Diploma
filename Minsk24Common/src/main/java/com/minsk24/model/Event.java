package com.minsk24.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by khadunai on 12/2/2016.
 */
@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String name;
    private String location;
    private String description;
    private String mainPhoto;
    private List<OpinionBeforeEvent> opinionsBeforeEvent;
    private List<EventRate> eventRates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public List<OpinionBeforeEvent> getOpinionsBeforeEvent() {
        return opinionsBeforeEvent;
    }

    public void setOpinionsBeforeEvent(List<OpinionBeforeEvent> opinionsBeforeEvent) {
        this.opinionsBeforeEvent = opinionsBeforeEvent;
    }

    public List<EventRate> getEventRates() {
        return eventRates;
    }

    public void setEventRates(List<EventRate> eventRates) {
        this.eventRates = eventRates;
    }
}
