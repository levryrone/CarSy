package com.carsy.exception;

public class FavoritesNotFoundException extends ProductException {
    public FavoritesNotFoundException(String message) {
        super(message);
    }
}
