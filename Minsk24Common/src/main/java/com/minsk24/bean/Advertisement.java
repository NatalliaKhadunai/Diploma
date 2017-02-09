package com.minsk24.bean;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="ADVERTISEMENT_SEQ")
    @SequenceGenerator(name="ADVERTISEMENT_SEQ", sequenceName="ADVERTISEMENT_SEQ", allocationSize=1)
    private int id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "HOLDER_ACCOUNT_ID", nullable = false)
    private Account holder;
    @Column(length=1000)
    private String description;
    @Column(name = "PLACEMENT_DATE")
    private Date placementDate;
    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;

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

    public Account getHolder() {
        return holder;
    }

    public void setHolder(Account holder) {
        this.holder = holder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        if (comments == null) comments = new ArrayList<>();
        comments.add(comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advertisement that = (Advertisement) o;

        if (id != that.id) return false;
        if (!title.equals(that.title)) return false;
        if (!holder.equals(that.holder)) return false;
        if (!description.equals(that.description)) return false;
        if (!placementDate.equals(that.placementDate)) return false;
        if (!expirationDate.equals(that.expirationDate)) return false;
        return comments != null ? comments.equals(that.comments) : that.comments == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + holder.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + placementDate.hashCode();
        result = 31 * result + expirationDate.hashCode();
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nAdvertisement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", holder=" + holder +
                ", content='" + description + '\'' +
                ", placementDate=" + placementDate +
                ", expirationDate=" + expirationDate +
                ", comments=" + comments +
                '}';
    }
}
