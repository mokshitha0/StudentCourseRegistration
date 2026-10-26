package com.example.demoService;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demoModel.Student;
import com.example.demoExecption.StudentNotFoundException;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();
    private long nextId = 1;

    // Register a student
    public Student registerStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException(
                "Student details cannot be null");
        }

        student.setStudentId(nextId++);
        students.add(student);

        return student;
    }

    // View all registered students
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // Find student by ID
    public Student getStudentById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                "Student ID must be positive");
        }

        return students.stream()
                .filter(student ->
                    student.getStudentId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new StudentNotFoundException(
                        "Student not found with ID: " + id));
    }

    // Delete student registration
    public String deleteStudent(Long id) {
        Student student = getStudentById(id);
        students.remove(student);

        return "Student registration deleted successfully";
    }
}
