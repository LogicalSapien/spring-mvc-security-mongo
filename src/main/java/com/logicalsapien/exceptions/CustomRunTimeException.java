package com.logicalsapien.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomRunTimeException extends RuntimeException{

    private String message;

    private transient Object[] args;

}
