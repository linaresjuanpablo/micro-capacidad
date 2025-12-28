package com.example.capacidad.infra.input.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor

public class ErrorResponse {

    private String name;
    private String description;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public ErrorResponse(String name, String description) {

        this.name = name;
        this.description = description;

    }
}
