package com.workshop.university.controller;

import com.workshop.university.model.Course;
import com.workshop.university.model.Student;
import com.workshop.university.repository.CourseRepository;
import com.workshop.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentRepository.findById(id).orElseThrow();

    }

    @PostMapping("/{studentId}/register/{courseId}")
    public Student registerToCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();
        student.getCourses().add(course);
        course.getStudents().add(student); // Maintain bidirectional consistency
        return studentRepository.save(student);
    }

}


