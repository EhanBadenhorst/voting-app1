package service;

import entity.Student;
import repository.StudentRepository;
import util.PasswordUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class AuthService {
    
    @Inject
    StudentRepository studentRepository;
    
    public Optional<Student> authenticate(String studentNo, String password) {
        if (studentNo == null || password == null || studentNo.trim().isEmpty()) {
            return Optional.empty();
        }
        
        // Find student by student number
        Optional<Student> studentOpt = studentRepository.findByStudentNo(studentNo.trim());
        
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            // Verify password
            if (PasswordUtil.checkPassword(password, student.getPassword())) {
                return Optional.of(student);
            }
        }
        
        // Return empty if student not found or password doesn't match
        return Optional.empty();
    }
    
    @Transactional
    public Student registerStudent(Student student, String plainPassword) {
        // Check if student number already exists
        if (studentRepository.existsByStudentNo(student.getStudentNo())) {
            throw new IllegalArgumentException("Student number already exists: " + student.getStudentNo());
        }
        
        // Validate required fields
        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        
        if (student.getSurname() == null || student.getSurname().trim().isEmpty()) {
            throw new IllegalArgumentException("Surname is required");
        }
        
        if (student.getStudentNo() == null || student.getStudentNo().trim().isEmpty()) {
            throw new IllegalArgumentException("Student number is required");
        }
        
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        
        // Hash password before saving
        student.setPassword(PasswordUtil.hashPassword(plainPassword));
        studentRepository.persist(student);
        return student;
    }
}