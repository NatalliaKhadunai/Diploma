package com.minsk24.bean;

import javax.persistence.*;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="TAG_SEQ")
    @SequenceGenerator(name="TAG_SEQ", sequenceName="TAG_SEQ", allocationSize=1)
    private int id;
    @Column(length = 30)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return getName().equals(tag.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode() + getId();
    }

    @Override
    public String toString() {
        return "\nTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
