package com.example.demoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.demoExecption.StudentNotFoundException;
import com.example.demoModel.Student;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();
    private long nextId = 1;

    // CREATE
    public Student registerStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student details cannot be null");
        }

        // A student cannot register for the same course twice using the same email.
        boolean duplicate = students.stream().anyMatch(existing ->
            existing.getEmail().equalsIgnoreCase(student.getEmail())
            && existing.getCourseName().equalsIgnoreCase(student.getCourseName()));

        if (duplicate) {
            throw new IllegalArgumentException(
                "This email is already registered for this course");
        }

        student.setStudentId(nextId++);
        students.add(student);
        return student;
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // READ BY ID
    public Student getStudentById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Student ID must be positive");
        }

        return students.stream()
            .filter(student -> Objects.equals(student.getStudentId(), id))
            .findFirst()
            .orElseThrow(() -> new StudentNotFoundException(
                "Student not found with ID: " + id));
    }

    // UPDATE
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = getStudentById(id);
        if (updatedStudent == null) {
            throw new IllegalArgumentException("Student details cannot be null");
        }

        boolean duplicate = students.stream()
            .filter(student -> !Objects.equals(student.getStudentId(), id))
            .anyMatch(student ->
                student.getEmail().equalsIgnoreCase(updatedStudent.getEmail())
                && student.getCourseName().equalsIgnoreCase(updatedStudent.getCourseName()));

        if (duplicate) {
            throw new IllegalArgumentException(
                "This email is already registered for this course");
        }

        existing.setStudentName(updatedStudent.getStudentName());
        existing.setCourseName(updatedStudent.getCourseName());
        existing.setEmail(updatedStudent.getEmail());
        existing.setAge(updatedStudent.getAge());
        return existing;
    }

    // DELETE
    public String deleteStudent(Long id) {
        Student student = getStudentById(id);
        students.remove(student);
        return "Student registration deleted successfully";
    }

    // CUSTOM METHOD 1: Search students by a partial, case-insensitive name.
    public List<Student> searchByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Search name cannot be empty");
        }

        String query = name.trim().toLowerCase();
        return students.stream()
            .filter(student -> student.getStudentName() != null
                && student.getStudentName().toLowerCase().contains(query))
            .toList();
    }

    // CUSTOM METHOD 2: Filter registrations by course name.
    public List<Student> getStudentsByCourse(String courseName) {
        if (courseName == null || courseName.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }

        return students.stream()
            .filter(student -> student.getCourseName() != null
                && student.getCourseName().equalsIgnoreCase(courseName.trim()))
            .toList();
    }

    // CUSTOM METHOD 3: Filter students within an inclusive age range.
    public List<Student> getStudentsByAgeRange(int minAge, int maxAge) {
        if (minAge < 18 || maxAge < 18 || minAge > maxAge) {
            throw new IllegalArgumentException(
                "Age range must be valid and both ages must be at least 18");
        }

        return students.stream()
            .filter(student -> student.getAge() >= minAge
                && student.getAge() <= maxAge)
            .toList();
    }

    // CUSTOM METHOD 4: Check whether an email is registered for a particular course.
    public boolean isRegisteredForCourse(String email, String courseName) {
        if (email == null || email.isBlank()
                || courseName == null || courseName.isBlank()) {
            throw new IllegalArgumentException(
                "Email and course name are required");
        }

        return students.stream().anyMatch(student ->
            student.getEmail().equalsIgnoreCase(email.trim())
            && student.getCourseName().equalsIgnoreCase(courseName.trim()));
    }
}
