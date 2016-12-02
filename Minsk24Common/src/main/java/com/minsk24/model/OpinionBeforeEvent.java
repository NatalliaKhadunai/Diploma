package com.minsk24.model;

/**
 * Created by khadunai on 12/2/2016.
 */
public class OpinionBeforeEvent {
    private int userId;
    private EventUserChoice eventUserChoice;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public EventUserChoice getEventUserChoice() {
        return eventUserChoice;
    }

    public void setEventUserChoice(EventUserChoice eventUserChoice) {
        this.eventUserChoice = eventUserChoice;
    }
}
