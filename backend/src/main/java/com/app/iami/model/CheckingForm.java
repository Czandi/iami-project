package com.app.iami.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckingForm {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "checkingForms")
    @JsonBackReference
    private Set<Course> courses;

//    @JoinTable(
//            name = "course_form",
//            joinColumns = @JoinColumn(name = "id_checking_form"),
//            inverseJoinColumns = @JoinColumn(name = "weight"))
//    @Column(nullable = false)
//    private Float weight;

//    @OneToMany(mappedBy = "checkingForm")
//    @JsonManagedReference
//    Set<CourseForm> weight;

}
