package com.jira.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="text")
    private String text;

    public Comment() {
    }

    public Comment(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && text.equals(comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
