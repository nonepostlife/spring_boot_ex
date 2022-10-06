package ru.postlife.boot.spring_boot_ex.core.services;

import ru.postlife.boot.spring_boot_ex.core.dto.StudentDto;
import ru.postlife.boot.spring_boot_ex.core.entities.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudents();

    Student findById(Long id);

    void addStudent(StudentDto studentDto);

    void updateStudent(StudentDto studentDto);

    void deleteStudent(Long id);
}
