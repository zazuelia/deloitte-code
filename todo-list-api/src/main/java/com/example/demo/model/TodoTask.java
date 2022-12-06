package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TodoTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskId;
    private String taskName;
    private Timestamp taskDate;
    private Timestamp lastUpdate;
    private boolean checked;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference // To prevent Stackoverflow Error
    private TodoUser todoUser;
}
