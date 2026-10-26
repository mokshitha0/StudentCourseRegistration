package com.example.demoController;
import java.util.List;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoModel.Student;
import com.example.demoService.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // Register student
    @PostMapping
    public Student registerStudent(
            @Valid @RequestBody Student student) {
        return service.registerStudent(student);
    }

    // View all students
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // Find student by ID
    @GetMapping("/{id}")
    public Student getStudentById(
            @PathVariable Long id) {
        return service.getStudentById(id);
    }

    // Delete registration
    @DeleteMapping("/{id}")
    public String deleteStudent(
            @PathVariable Long id) {
        return service.deleteStudent(id);
    }
}
