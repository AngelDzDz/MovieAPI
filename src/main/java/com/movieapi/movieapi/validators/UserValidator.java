package com.movieapi.movieapi.validators;

import com.movieapi.movieapi.entities.User;
import com.movieapi.movieapi.exceptions.ValidateServiceException;

public class UserValidator {
    public static void signup(User user) {
        if(user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }

        if(user.getUsername().length() > 30) {
            throw new ValidateServiceException("El nombre de usuario es muy largo! (máx 30)");
        }

        if(user.getPassword() == null|| user.getPassword().isEmpty()) {
            throw new ValidateServiceException("La contraseña es requerida!");
        }

        if(user.getPassword().length() > 30) {
            throw new ValidateServiceException("El password es demasiado largo! (max 30)");
        }

    }
}
