package com.movieapi.movieapi.validators;

import com.movieapi.movieapi.entities.Genre;
import com.movieapi.movieapi.exceptions.ValidateServiceException;

public class GenreValidator {
    public static void save(Genre genre) {

        if(genre.getName() == null || genre.getName().isEmpty()) {
            throw new ValidateServiceException("El nombre del género es necesario!");
        }

        if (genre.getName().length() > 100) {
            throw new ValidateServiceException("El nombre del género es demasiado largo!");
        }
    }
}
