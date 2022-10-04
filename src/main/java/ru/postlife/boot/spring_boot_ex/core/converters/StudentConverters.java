package ru.postlife.boot.spring_boot_ex.core.converters;

import org.springframework.stereotype.Component;
import ru.postlife.boot.spring_boot_ex.core.dto.StudentDto;
import ru.postlife.boot.spring_boot_ex.core.entities.Student;

@Component
public class StudentConverters {

    public StudentDto entityToDto(Student student) {
        return new StudentDto(student.getId(), student.getName(), student.getAge());
    }
}
