package com.minsk24.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
@JsonIgnoreProperties({"password"})
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ACC_ID")
    private Integer id;
    @Column(name = "LOGIN", unique = true, length = 30, nullable = false)
    private String login;
    @Column(name="PASSWORD", length = 30, nullable = false)
    private String password;
    @Column(name = "ROLE", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ACCOUNT_INTERESTING_TAGS", joinColumns = {
            @JoinColumn(name = "ACCOUNT_ID", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "TAG_ID",
                    nullable = false, updatable = false) })
    private List<Tag> interestingTags = new ArrayList<>();
    @Column(name = "PHOTO", length = 20, columnDefinition="default 'defaultPhoto'")
    private String photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Tag> getInterestingTags() {
        return interestingTags;
    }

    public void setInterestingTags(List<Tag> interestingTags) {
        this.interestingTags = interestingTags;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != null ? !id.equals(account.id) : account.id != null) return false;
        if (login != null ? !login.equals(account.login) : account.login != null) return false;
        if (password != null ? !password.equals(account.password) : account.password != null) return false;
        if (role != account.role) return false;
        if (interestingTags != null ? !interestingTags.equals(account.interestingTags) : account.interestingTags != null)
            return false;
        return photo != null ? photo.equals(account.photo) : account.photo == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (interestingTags != null ? interestingTags.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", interestingTags=" + interestingTags +
                ", photo='" + photo + '\'' +
                '}';
    }
}
