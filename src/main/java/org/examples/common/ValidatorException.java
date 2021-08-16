package org.examples.common;

public class ValidatorException extends RuntimeException{
    public ValidatorException(String parameter) {
        super("Invalid parameter: "+parameter);
    }
}