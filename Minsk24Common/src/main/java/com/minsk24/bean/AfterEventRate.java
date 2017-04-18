package com.minsk24.bean;

import javax.persistence.*;

@Entity
@Table(name = "AFTER_EVENT_RATE",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"EVENT_ID", "ACCOUNT_ID"})})
public class AfterEventRate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "AER_ID")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private Event event;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account user;
    @Column(name = "RATE", nullable = false)
    private Integer rate;

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

        AfterEventRate that = (AfterEventRate) o;

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
