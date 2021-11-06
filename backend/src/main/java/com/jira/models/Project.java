package com.jira.models;

import com.jira.Validator.ModelValidator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ModelValidator
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String linkToGit;
    private int progress;


//    private Status status; ?
//    private Team team; ?
}
