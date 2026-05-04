package com.workshop.university.controller;

import com.workshop.university.model.Course;
import com.workshop.university.model.Instructor;
import com.workshop.university.repository.CourseRepository;
import com.workshop.university.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Instructors")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    @PostMapping
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }


    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByInstructor(@PathVariable Long id) {
        return instructorRepository.findById(id)
                .map(instructor -> ResponseEntity.ok(instructor.getCourses()))
                .orElse(ResponseEntity.notFound().build());
    }
}
