package com.minsk24.bean;

import javax.persistence.*;

@Entity
@Table(name = "USER_AFTER_EVENT_RATE")
public class UserAfterEventRate {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="USER_AFTER_EVENT_RATE_SEQ")
    @SequenceGenerator(name="USER_AFTER_EVENT_RATE_SEQ", sequenceName="USER_AFTER_EVENT_RATE_SEQ", allocationSize=1)
    private int id;
    @ManyToOne
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private Event event;
    @ManyToOne
    @JoinColumn(name = "USER_ACCOUNT_ID", nullable = false)
    private Account user;
    private int rate;

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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAfterEventRate that = (UserAfterEventRate) o;

        if (id != that.id) return false;
        if (rate != that.rate) return false;
        if (!event.equals(that.event)) return false;
        return user.equals(that.user);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + event.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + rate;
        return result;
    }

    @Override
    public String toString() {
        return "\nUserAfterEventRate{" +
                "id=" + id +
                ", event=" + event +
                ", user=" + user +
                ", rate=" + rate +
                '}';
    }
}
