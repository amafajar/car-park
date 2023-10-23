package com.develab.carpark.adapter.input.rest.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException{

    private String field;
    private String errorMessage;
}
