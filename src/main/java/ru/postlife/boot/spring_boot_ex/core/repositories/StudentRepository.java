package ru.postlife.boot.spring_boot_ex.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.postlife.boot.spring_boot_ex.core.entities.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
