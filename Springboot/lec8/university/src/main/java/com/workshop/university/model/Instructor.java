package com.workshop.university.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // One instructor can teach many courses
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("instructor")
    private List<Course> courses;
}

