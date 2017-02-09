package com.minsk24.bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="ARTICLE_SEQ")
    @SequenceGenerator(name="ARTICLE_SEQ", sequenceName="ARTICLE_SEQ", allocationSize=1)
    private int id;
    @Column(name = "MAIN_TITLE")
    private String mainTitle;
    @Column(name = "SHORT_TITLE", length=200)
    private String shortTitle;
    @Column(name = "PUBLISH_DATE")
    private Timestamp publishDate;
    @Column(length = 2000)
    private String content;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ARTICLE_AUTHOR", joinColumns = {
            @JoinColumn(name = "ARTICLE_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "AUTHOR_ACCOUNT_ID", nullable = false, updatable = false) })
    private Set<Account> authors;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ARTICLE_TAG", joinColumns = {
            @JoinColumn(name = "ARTICLE_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "TAG_ID", nullable = false, updatable = false) })
    private Set<Tag> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
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

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Account> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Account> authors) {
        this.authors = authors;
    }

    public void addAuthor(Account author) {
        if (authors == null) authors = new HashSet<Account>();
        authors.add(author);
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        if (tags == null) tags = new HashSet<Tag>();
        tags.add(tag);
    }

    public void addComment(Comment comment) {
        if (comments == null) comments = new ArrayList<Comment>();
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "\nArticle{" +
                "id=" + id +
                ", mainTitle='" + mainTitle + '\'' +
                ", shortTitle='" + shortTitle + '\'' +
                ", publishDate=" + publishDate +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                ", authors=" + authors +
                ", tags=" + tags +
                '}';
    }
}
