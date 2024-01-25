package com.movieapi.movieapi.services;

import com.movieapi.movieapi.entities.Genre;
import com.movieapi.movieapi.entities.Movie;
import com.movieapi.movieapi.entities.User;
import com.movieapi.movieapi.exceptions.GeneralServiceException;
import com.movieapi.movieapi.exceptions.NoAuthorizationException;
import com.movieapi.movieapi.exceptions.NoDataFoundException;
import com.movieapi.movieapi.exceptions.ValidateServiceException;
import com.movieapi.movieapi.repository.GenreRepository;
import com.movieapi.movieapi.repository.MovieRepository;
import com.movieapi.movieapi.repository.UserRepository;
import com.movieapi.movieapi.security.UserPrincipal;
import com.movieapi.movieapi.validators.MovieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Slf4j
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Movie> findAll() {
        try{
            User user = UserPrincipal.getTheUser();
            return movieRepository.findByUser(user);
        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }

    }

    public Movie findById(Long id) {
        try{
            User user = UserPrincipal.getTheUser();
            Movie movie = movieRepository.findById(id).orElseThrow(() -> new NoDataFoundException("No existe la película!"));
            if(movie.getUser().getUsername().equals(user.getUsername())) {
                return movie;
            }
            throw new NoAuthorizationException("No tienes permisos para acceder a esta película!");

        }catch(ValidateServiceException | NoDataFoundException | NoAuthorizationException e) {
            log.info(e.getMessage(),e);
            throw e;
        } catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }
    @Transactional
    public Movie save(Movie movie) {

       try{
           User user = UserPrincipal.getTheUser(); //Se obtiene el usuario logueado

           MovieValidator.save(movie);
           Genre foundGenre = genreRepository.findById(movie.getGenre().getId()).orElseThrow(()->new NoDataFoundException("El género no existe!"));
           User foundUser = userRepository.findById(user.getId()).orElseThrow(()-> new NoDataFoundException("El usuario no existe!"));
           movie.setGenre(foundGenre);
           movie.setUser(user);

           if(movie.getId() == null) {
               movie.setUser(user);
               return movieRepository.save(movie);
           }

           Movie foundMovie = movieRepository.findById(movie.getId()).orElseThrow(() -> new NoDataFoundException("No existe la película!"));

           if(foundMovie.getUser().getUsername().equals(user.getUsername())) {
               foundMovie.setTitle(movie.getTitle());
               foundMovie.setGenre(movie.getGenre());
               foundMovie.setDirector(movie.getDirector());
               foundMovie.setYearOfRelease(movie.getYearOfRelease());
               foundMovie.setDescription(movie.getDescription());
               foundMovie.setDuration(movie.getDuration());
               foundMovie.setStars(movie.getStars());
               foundMovie.setIsWatched(movie.getIsWatched());
               foundMovie.setUser(user);

               movieRepository.save(foundMovie);
               return foundMovie;
           }
           throw new NoAuthorizationException("No tienes permisos para editar esta película!");


       }catch(ValidateServiceException | NoDataFoundException | NoAuthorizationException e) {
           log.info(e.getMessage(),e);
           throw e;
       }catch(Exception e) {
           log.error(e.getMessage(),e);
           throw new GeneralServiceException(e.getMessage(),e);
       }
    }

    @Transactional
    public void delete(Long id) {
        try{
            User user = UserPrincipal.getTheUser();
            Movie movie = movieRepository.findById(id).orElseThrow(()->new NoDataFoundException("No existe la película!"));

            if(movie.getUser().getUsername().equals(user.getUsername())) {
                movieRepository.delete(movie);
            }
            throw new NoAuthorizationException("No tienes permisos para eliminar esta película!");

        }catch(ValidateServiceException | NoDataFoundException | NoAuthorizationException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }

    }

    public List<Movie> findToWatchMovies() {
        try{
            User user = UserPrincipal.getTheUser();
            return movieRepository.findByUserIdAndIsWatched(user.getId(),false);
        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }


    public List<Movie> findWatchedMovies() {
        try{
            User user = UserPrincipal.getTheUser();
            return movieRepository.findByUserIdAndIsWatched(user.getId(),true);
        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

    @Transactional
    public Movie saveIsWatched(Movie movie,boolean isWatched) {
        try{
            MovieValidator.saveIsWatched(movie);
            Genre foundGenre = genreRepository.findById(movie.getGenre().getId()).orElseThrow(()->new NoDataFoundException("El género no existe!"));
            //User foundUser = userRepository.findById(userID).orElseThrow(()-> new NoDataFoundException("El usuario no existe!"));
            User user = UserPrincipal.getTheUser();
            movie.setGenre(foundGenre);
            movie.setUser(user);
            movie.setIsWatched(isWatched);
            return movieRepository.save(movie);

        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }

    }


}
