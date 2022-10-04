package ru.postlife.boot.spring_boot_ex.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.postlife.boot.spring_boot_ex.core.converters.StudentConverters;
import ru.postlife.boot.spring_boot_ex.core.dto.StudentDto;
import ru.postlife.boot.spring_boot_ex.core.exceptions.AppError;
import ru.postlife.boot.spring_boot_ex.core.services.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Tag(name = "Student", description = "Методы работы со студентами")
public class StudentController {
    private final StudentService studentService;
    private final StudentConverters studentConverters;

    @Operation(
            summary = "Запрос на получение списка всех студентов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StudentDto.class))
                    )
            }
    )
    @GetMapping()
    public List<StudentDto> getAllStudents() {
        return studentService.findAllStudents().stream().map(studentConverters::entityToDto).collect(Collectors.toList());
    }

    @Operation(
            summary = "Запрос на получение студента по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StudentDto.class))
                    ),
                    @ApiResponse(
                            description = "Студент не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable @Parameter(description = "Идентификатор студента", required = true) Long id) {
        return studentConverters.entityToDto(studentService.findById(id));
    }

    @Operation(
            summary = "Запрос на добавление нового студента",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addNewStudent(@RequestBody @Parameter(description = "Информация о студенте", required = true) StudentDto student) {
        studentService.addStudent(student);
        return new ResponseEntity<>("Студент " + student.getName() + " был успешно добавлен", HttpStatus.CREATED);
    }

    @Operation(
            summary = "Запрос на изменение студента",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Студент не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @PutMapping
    public ResponseEntity<String> updateStudent(@RequestBody @Parameter(description = "Информация о студенте", required = true) StudentDto student) {
        studentService.updateStudent(student);
        return new ResponseEntity<>("Студент c id=" + student.getId() + " был успешно изменен", HttpStatus.OK);
    }

    @Operation(
            summary = "Запрос на удаление студента по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Студент не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable @Parameter(description = "Идентификатор студента", required = true) Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Студент c id=" + id + " был успешно удален", HttpStatus.OK);
    }
}
