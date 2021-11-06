package com.jira.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String description;
    private LocalDateTime dateTime;
    private int spentTime;

   /* @OneToMany   ?
    private Comment comment;*/
}
