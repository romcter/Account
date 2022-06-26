package com.shop.account.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5203852665227340049L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
