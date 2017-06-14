package com.minsk24.bean;

import javax.persistence.*;

@Entity
@Table(name = "BEFORE_EVENT_RATE",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"EVENT_ID", "ACCOUNT_ID"})})
public class BeforeEventRate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "BER_ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private Event event;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account user;
    @Column(name = "USER_CHOICE", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private EventUserChoice eventUserChoice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

        BeforeEventRate that = (BeforeEventRate) o;

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
