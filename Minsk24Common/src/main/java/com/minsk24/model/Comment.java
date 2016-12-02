package com.minsk24.model;

import java.sql.Timestamp;

public class Comment {
    private User user;
    private Timestamp date;
    private String content;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (!user.equals(comment.user)) return false;
        if (!date.equals(comment.date)) return false;
        return content.equals(comment.content);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "user=" + user +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }
}
