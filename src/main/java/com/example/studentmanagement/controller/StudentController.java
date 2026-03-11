package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // POST /api/students - Creates a new student record
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        // Validate incoming requests to ensure required fields are present
        if (student.getName() == null || student.getName().trim().isEmpty() ||
            student.getEmail() == null || student.getEmail().trim().isEmpty() ||
            student.getCourse() == null || student.getCourse().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Error: Name, Email, and Course are required fields.");
        }

        try {
            // Pass to the service layer for business logic and saving
            Student savedStudent = studentService.saveStudent(student);
            return ResponseEntity.ok(savedStudent);
        } catch (IllegalArgumentException e) {
            // This catches the duplicate email error we wrote in the Service layer
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/students - Fetches a list of all student records
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }
}