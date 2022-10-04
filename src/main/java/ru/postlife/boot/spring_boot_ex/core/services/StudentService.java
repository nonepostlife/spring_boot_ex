package ru.postlife.boot.spring_boot_ex.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.postlife.boot.spring_boot_ex.core.dto.StudentDto;
import ru.postlife.boot.spring_boot_ex.core.entities.Student;
import ru.postlife.boot.spring_boot_ex.core.exceptions.ResourceNotFoundException;
import ru.postlife.boot.spring_boot_ex.core.repositories.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Студент с id: '" + id + "' не найден"));
    }

    public void addStudent(StudentDto studentDto) {
        studentRepository.save(new Student(0L, studentDto.getName(), studentDto.getAge()));
    }

    public void updateStudent(StudentDto studentDto) {
        findById(studentDto.getId());
        studentRepository.save(new Student(studentDto.getId(), studentDto.getName(), studentDto.getAge()));
    }

    public void deleteStudent(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Студент с id: '" + id + "' не найден");
        }
    }
}
