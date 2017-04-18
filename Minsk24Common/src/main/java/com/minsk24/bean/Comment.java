package com.minsk24.bean;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "C_ID")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account publisher;
    @Column(name = "PUBLISH_DATE", nullable = false)
    private Timestamp publishDate;
    @Column(name = "CONTENT", length = 300, nullable = false)
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Account getPublisher() {
        return publisher;
    }

    public void setPublisher(Account publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (!publisher.equals(comment.publisher)) return false;
        if (!publishDate.equals(comment.publishDate)) return false;
        return content.equals(comment.content);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + publisher.hashCode();
        result = 31 * result + publishDate.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nComment{" +
                "id=" + id +
                ", publisher=" + publisher +
                ", publishDate=" + publishDate +
                ", content='" + content + '\'' +
                '}';
    }
}
