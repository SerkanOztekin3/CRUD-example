package com.example.demo.student.services;


import com.example.demo.student.model.Student;
import com.example.demo.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;

@Component
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public long addNewStudent(Student student) {
        long id = studentRepository.save(student).getId();
        return id;    //updateStudent methodunu oluştur...
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id" + studentId + "does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

}
