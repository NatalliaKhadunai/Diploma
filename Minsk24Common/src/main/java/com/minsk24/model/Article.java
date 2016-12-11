package com.minsk24.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Document(collection = "articles")
public class Article {
    @Id
    private String id;
    private String mainTitle;
    private String shortTitle;
    private String content;
    private Date publishDate;
    private String mainPhoto;
    private User author;
    private Set<String> tags;
    private List<Comment> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        if (mainTitle.length() <= 230) this.mainTitle = mainTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        if (shortTitle.length() <= 50) this.shortTitle = shortTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content.length() <= 5000) this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;

        Article article = (Article) o;

        if (!id.equals(article.id)) return false;
        if (!mainTitle.equals(article.mainTitle)) return false;
        if (!shortTitle.equals(article.shortTitle)) return false;
        if (!content.equals(article.content)) return false;
        if (!publishDate.equals(article.publishDate)) return false;
        if (!mainPhoto.equals(article.mainPhoto)) return false;
        if (!author.equals(article.author)) return false;
        if (!tags.equals(article.tags)) return false;
        return comments.equals(article.comments);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + mainTitle.hashCode();
        result = 31 * result + shortTitle.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + publishDate.hashCode();
        result = 31 * result + mainPhoto.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + tags.hashCode();
        result = 31 * result + comments.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", mainTitle='" + mainTitle + '\'' +
                ", shortTitle='" + shortTitle + '\'' +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate +
                ", mainPhoto='" + mainPhoto + '\'' +
                ", author=" + author +
                ", tags=" + tags +
                ", comments=" + comments +
                '}';
    }
}
