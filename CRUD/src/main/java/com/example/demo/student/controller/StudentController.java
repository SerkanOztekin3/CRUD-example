package com.example.demo.student.controller;

import com.example.demo.student.model.Student;
import com.example.demo.student.services.StudentService;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired 
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }


    @PostMapping(path = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long registerNewStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "/{studentId}")
    public long deleteStudent(
            @PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        studentService.deleteStudent(studentId);
        return student.getId();
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public long update(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }
}
