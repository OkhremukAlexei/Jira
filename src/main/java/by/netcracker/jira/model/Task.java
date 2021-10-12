package by.netcracker.jira.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Task")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    //TODO add attributes
}
