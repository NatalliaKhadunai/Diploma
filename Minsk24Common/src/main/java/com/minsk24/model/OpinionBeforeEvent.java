package com.minsk24.model;

/**
 * Created by khadunai on 12/2/2016.
 */
public class OpinionBeforeEvent {
    private User user;
    private EventUserChoice eventUserChoice;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventUserChoice getEventUserChoice() {
        return eventUserChoice;
    }

    public void setEventUserChoice(EventUserChoice eventUserChoice) {
        this.eventUserChoice = eventUserChoice;
    }
}
