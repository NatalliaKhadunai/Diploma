package com.minsk24.bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ARTICLE")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ART_ID")
    private Integer id;
    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;
    @Column(name = "SHORT_DESCRIPTION", length = 200, nullable = false)
    private String shortDescription;
    @Column(name = "PUBLISH_DATE", nullable = false)
    private Timestamp publishDate;
    @Column(name = "CONTENT", length = 4000, nullable = false)
    private String content;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Account author;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ARTICLE_TAG", joinColumns = {
            @JoinColumn(name = "ARTICLE_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "TAG_ID",
                    nullable = false, updatable = false)})
    private Set<Tag> tags = new HashSet<>();

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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != article.id) return false;
        if (!title.equals(article.title)) return false;
        if (!shortDescription.equals(article.shortDescription)) return false;
        if (!publishDate.equals(article.publishDate)) return false;
        if (!content.equals(article.content)) return false;
        if (comments != null ? !comments.equals(article.comments) : article.comments != null) return false;
        if (!author.equals(article.author)) return false;
        return tags.equals(article.tags);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + shortDescription.hashCode();
        result = 31 * result + publishDate.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + author.hashCode();
        result = 31 * result + tags.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", publishDate=" + publishDate +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                ", author=" + author +
                ", tags=" + tags +
                '}';
    }
}
