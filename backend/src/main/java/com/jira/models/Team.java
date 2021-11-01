package com.jira.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="numberOfPersons")
    private int numberOfPersons;

    public Team() {
    }

    public Team(int id, int numberOfPersons) {
        this.id = id;
        this.numberOfPersons = numberOfPersons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id && numberOfPersons == team.numberOfPersons;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfPersons);
    }
}
