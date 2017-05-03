package com.minsk24.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//TODO: add date field
@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "E_ID")
    private Integer id;
    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;
    @Column(name = "LOCATION", length = 100, nullable = false)
    private String location;
    @Column(name = "DESCRIPTION", length = 1000, nullable = false)
    private String description;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
