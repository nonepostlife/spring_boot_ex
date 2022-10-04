package ru.postlife.boot.spring_boot_ex.core.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.postlife.boot.spring_boot_ex.core.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
