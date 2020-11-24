package com.app.iami.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id_student"))
    @JsonManagedReference
    private Set<Student> students;

//    @OneToMany
//    @JoinTable(
//            name = "course_form",
//            joinColumns = @JoinColumn(name = "id_course"),
//            inverseJoinColumns = @JoinColumn(name = "id_checking_form"))
//    @JsonManagedReference
//    private Set<CheckingForm> checkingForms;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_form",
            joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id_checking_form"))
    @JsonManagedReference
    private Set<CheckingForm> checkingForms;

    private String date;

}
