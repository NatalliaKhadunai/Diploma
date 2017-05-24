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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!id.equals(account.id)) return false;
        if (!login.equals(account.login)) return false;
        if (!password.equals(account.password)) return false;
        if (role != account.role) return false;
        return interestingTags != null
                ? interestingTags.equals(account.interestingTags)
                : account.interestingTags == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + (interestingTags != null ? interestingTags.hashCode() : 0);
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
                '}';
    }
}
