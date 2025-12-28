package com.example.capacidad.domain.exception.error;

public interface IErrorCode {

    String getCode();
    String getMessage();
    int getStatusCode();

    ErrorCapability getCapability();
}
