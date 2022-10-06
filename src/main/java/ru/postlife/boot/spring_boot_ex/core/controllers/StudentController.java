package ru.postlife.boot.spring_boot_ex.core.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.postlife.boot.spring_boot_ex.core.dto.StudentDto;

import java.util.List;

public interface StudentController {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(@PathVariable @Parameter(description = "Идентификатор студента", required = true) Long id);

    ResponseEntity<String> addNewStudent(@RequestBody @Parameter(description = "Информация о студенте", required = true) StudentDto student);

    ResponseEntity<String> updateStudent(@RequestBody @Parameter(description = "Информация о студенте", required = true) StudentDto student);

    ResponseEntity<String> deleteStudentById(@PathVariable @Parameter(description = "Идентификатор студента", required = true) Long id);
}
