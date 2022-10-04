package ru.postlife.boot.spring_boot_ex.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class StudentDto {

    @Schema(description = "ID студента", required = true, example = "1")
    private Long id;
    @Schema(description = "Имя студента", required = true, example = "Ваня")
    private String name;
    @Schema(description = "Возраст студента", required = true, example = "19")
    private int age;

    public StudentDto(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
