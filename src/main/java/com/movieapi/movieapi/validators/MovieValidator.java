package com.movieapi.movieapi.validators;

import com.movieapi.movieapi.entities.Movie;
import com.movieapi.movieapi.exceptions.NoDataFoundException;
import com.movieapi.movieapi.exceptions.ValidateServiceException;

public class MovieValidator {

    public static void save(Movie movie) {
        if(movie.getGenre() == null || movie.getGenre().getId() == null) {
            throw new ValidateServiceException("El género es requerido!");
        }

        if(movie.getTitle() == null|| movie.getTitle().trim().isEmpty()) {
            throw new ValidateServiceException("El título es requerido!");
        }
        if(movie.getDirector() == null || movie.getDirector().trim().isEmpty()) {
            throw new NoDataFoundException("El director es requerido");
        }

        if(movie.getYearOfRelease() == null) {
            throw new ValidateServiceException("El año es requerido");
        }

        if(movie.getDescription() == null || movie.getDescription().trim().isEmpty()) {
            throw new NoDataFoundException("La descripción es requerida");
        }

        if(movie.getDuration() == null) {
            throw new ValidateServiceException("La duración es requerida");
        }
        if(movie.getStars() == null) {
            throw new ValidateServiceException("Las estrellas son requeridas");
        }
        if(movie.getIsWatched() == null) {
            throw new ValidateServiceException("El estatus de la película es requerido");
        }

       /* if(movie.getUser() == null || movie.getUser().getId() == null) {
            throw new ValidateServiceException("El usuario es requerido!");
        }*/

        if(movie.getTitle().length() > 100) {
            throw new ValidateServiceException("El título es demasiado largo (max 100 carácteres)");
        }
        if(movie.getDirector().length() > 100) {
            throw new ValidateServiceException("El nombre del director es demasiado largo (max 100 carácteres)");
        }
        if(movie.getDescription().length() > 100) {
            throw new ValidateServiceException("La descripción es demasiado larga (max 100 carácteres)");
        }

        if(movie.getYearOfRelease() < 1800 || movie.getYearOfRelease()>2200) {
            throw  new ValidateServiceException("Ingrese un año válido!");
        }

        if(movie.getStars() <1 || movie.getStars()>5) {
            throw new ValidateServiceException("Las estrellas posibles son de 1 a 5");
        }

        if(movie.getDuration() < 9) {
            throw new ValidateServiceException("La duración debe de ser mayor a 10 min");
        }
    }

    public static void saveIsWatched(Movie movie) {
        if(movie.getGenre() == null || movie.getGenre().getId() == null) {
            throw new ValidateServiceException("El género es requerido!");
        }

        if(movie.getTitle() == null|| movie.getTitle().trim().isEmpty()) {
            throw new ValidateServiceException("El título es requerido!");
        }
        if(movie.getDirector() == null || movie.getDirector().trim().isEmpty()) {
            throw new NoDataFoundException("El director es requerido");
        }

        if(movie.getYearOfRelease() == null) {
            throw new ValidateServiceException("El año es requerido");
        }

        if(movie.getDescription() == null || movie.getDescription().trim().isEmpty()) {
            throw new NoDataFoundException("La descripción es requerida");
        }

        if(movie.getDuration() == null) {
            throw new ValidateServiceException("La duración es requerida");
        }
        if(movie.getStars() == null) {
            throw new ValidateServiceException("Las estrellas son requeridas");
        }


        if(movie.getTitle().length() > 100) {
            throw new ValidateServiceException("El título es demasiado largo (max 100 carácteres)");
        }
        if(movie.getDirector().length() > 100) {
            throw new ValidateServiceException("El nombre del director es demasiado largo (max 100 carácteres)");
        }
        if(movie.getDescription().length() > 100) {
            throw new ValidateServiceException("La descripción es demasiado larga (max 100 carácteres)");
        }
        if(movie.getYearOfRelease() < 1800 || movie.getYearOfRelease()>2200) {
            throw  new ValidateServiceException("Ingrese un año válido!");
        }
        if(movie.getStars() <1 || movie.getStars()>5) {
            throw new ValidateServiceException("Las estrellas posibles son de 1 a 5");
        }
        if(movie.getDuration() < 9) {
            throw new ValidateServiceException("La duración debe de ser mayor a 10 min");
        }
    }
}
