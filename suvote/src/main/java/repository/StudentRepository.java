package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import entity.Student;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {
    
    public Optional<Student> findByStudentNo(String studentNo) {
        return find("studentNo", studentNo).firstResultOptional();
    }
    
    public boolean existsByStudentNo(String studentNo) {
        return find("studentNo", studentNo).count() > 0;
    }
}