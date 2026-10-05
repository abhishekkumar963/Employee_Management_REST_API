package com.employeemanagement.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super(String.format("Employee with email '%s' already exists", email));
    }
}
