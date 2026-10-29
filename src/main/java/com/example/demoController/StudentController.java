package com.example.demoController;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // CREATE
    @PostMapping
    public Student registerStudent(@Valid @RequestBody Student student) {
        return service.registerStudent(student);
    }

    // READ ALL
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {
        return service.updateStudent(id, student);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return service.deleteStudent(id);
    }

    // CUSTOM 1: Search by name 
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) {
        return service.searchByName(name);
    }

    // CUSTOM 2: Filter by course
    
    @GetMapping("/course/{courseName}")
    public List<Student> getStudentsByCourse(
            @PathVariable String courseName) {
        return service.getStudentsByCourse(courseName);
    }

    // CUSTOM 3: Filter by Age
    @GetMapping("/age-range")
    public List<Student> getStudentsByAgeRange(
            @RequestParam int minAge,
            @RequestParam int maxAge) {
        return service.getStudentsByAgeRange(minAge, maxAge);
    }

    // CUSTOM 4:Check registration
    @GetMapping("/check-registration")
    public Map<String, Boolean> checkRegistration(
            @RequestParam String email,
            @RequestParam String courseName) {
        return Map.of("registered",
            service.isRegisteredForCourse(email, courseName));
    }
}
