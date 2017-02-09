package com.minsk24.bean;

import javax.persistence.*;

@Entity
@Table(name = "USER_BEFORE_EVENT_RATE")
public class UserBeforeEventRate {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="USER_BEFORE_EVENT_RATE_SEQ")
    @SequenceGenerator(name="USER_BEFORE_EVENT_RATE_SEQ", sequenceName="USER_BEFORE_EVENT_RATE_SEQ", allocationSize=1)
    private int id;
    @ManyToOne
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private Event event;
    @ManyToOne
    @JoinColumn(name = "USER_ACCOUNT_ID", nullable = false)
    private Account user;
    @Column(name = "USER_CHOICE", length = 15)
    @Enumerated(EnumType.STRING)
    private EventUserChoice eventUserChoice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public EventUserChoice getEventUserChoice() {
        return eventUserChoice;
    }

    public void setEventUserChoice(EventUserChoice eventUserChoice) {
        this.eventUserChoice = eventUserChoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBeforeEventRate that = (UserBeforeEventRate) o;

        if (id != that.id) return false;
        if (!event.equals(that.event)) return false;
        if (!user.equals(that.user)) return false;
        return eventUserChoice == that.eventUserChoice;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + event.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + eventUserChoice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nUserBeforeEventRate{" +
                "id=" + id +
                ", event=" + event +
                ", user=" + user +
                ", eventUserChoice=" + eventUserChoice +
                '}';
    }
}
