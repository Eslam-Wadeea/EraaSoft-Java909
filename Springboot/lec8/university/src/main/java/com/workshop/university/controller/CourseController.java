package com.workshop.university.controller;

import com.workshop.university.model.Course;
import com.workshop.university.model.Instructor;
import com.workshop.university.repository.CourseRepository;
import com.workshop.university.repository.InstructorRepository;
import com.workshop.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseRepository.save(course);
    }

    @GetMapping
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    @PutMapping("/{courseId}/assign-instructor/{instructorId}")
    public Instructor assignInstructor(@PathVariable Long instructorId, @PathVariable Long courseId){
        Instructor instructor = instructorRepository.findById(instructorId).get();
        Course course = courseRepository.findById(courseId).get();
        course.setInstructor(instructor);
        return instructorRepository.save(instructor);
   }


}
