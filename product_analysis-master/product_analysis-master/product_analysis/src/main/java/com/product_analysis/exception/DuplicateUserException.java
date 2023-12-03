package com.product_analysis.exception;


public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException() {
        super();
    }

    public DuplicateUserException(String message) {
        super(message);
    }

    // You can add additional constructors or methods as needed
}
