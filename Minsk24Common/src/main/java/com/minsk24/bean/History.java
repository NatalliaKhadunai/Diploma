package com.minsk24.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HISTORY")
public class History {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "E_ID")
    private Integer id;
    @Column(name = "content", length = 4000)
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
