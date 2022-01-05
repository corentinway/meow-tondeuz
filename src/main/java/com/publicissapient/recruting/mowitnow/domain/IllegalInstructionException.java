package com.publicissapient.recruting.mowitnow.domain;

public class IllegalInstructionException extends Exception {
    public IllegalInstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInstructionException(String message) {
        super(message);
    }
}
