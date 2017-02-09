package com.minsk24.bean;

import javax.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="EVENT_SEQ")
    @SequenceGenerator(name="EVENT_SEQ", sequenceName="EVENT_SEQ", allocationSize=1)
    private int id;
    private String title;
    private String location;
    @Column(length=1000)
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (!title.equals(event.title)) return false;
        if (!location.equals(event.location)) return false;
        return description.equals(event.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nEvent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location=" + location +
                ", description='" + description + '\'' +
                '}';
    }
}
