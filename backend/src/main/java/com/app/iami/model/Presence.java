package com.app.iami.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Presence {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    private boolean presence;

    private Date date;

}
