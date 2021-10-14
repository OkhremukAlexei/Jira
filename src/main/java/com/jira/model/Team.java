package com.jira.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "numberofpersons", nullable = false)
    private int numberOfPersons;

    public Team(Long id, int numberOfPersons) {
        this.id = id;
        this.numberOfPersons = numberOfPersons;
    }

    public Team(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;

        if (numberOfPersons != team.numberOfPersons) return false;
        return id.equals(team.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + numberOfPersons;
        return result;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", numberOfPersons=" + numberOfPersons +
                '}';
    }
}
