package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Save student with duplicate email check
    public Student saveStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("A student with this email already exists.");
        }
        return studentRepository.save(student);
    }

    // Retrieve all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}