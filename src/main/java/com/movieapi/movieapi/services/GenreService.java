package com.movieapi.movieapi.services;

import com.movieapi.movieapi.entities.Genre;
import com.movieapi.movieapi.entities.Movie;
import com.movieapi.movieapi.exceptions.GeneralServiceException;
import com.movieapi.movieapi.exceptions.NoDataFoundException;
import com.movieapi.movieapi.exceptions.ValidateServiceException;
import com.movieapi.movieapi.repository.GenreRepository;
import com.movieapi.movieapi.repository.MovieRepository;
import com.movieapi.movieapi.validators.GenreValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class GenreService{
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieRepository movieRepository;

    public List<Genre> findAll() {
        try{
            return genreRepository.findAll();
        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

    public Genre findById(Integer id) {
        try{
            return genreRepository.findById(id).orElseThrow(()-> new NoDataFoundException("No existe el género!"));
        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }
    @Transactional
    public Genre save(Genre genre) {
        try{
            GenreValidator.save(genre);
            Genre foundGenre = genreRepository.findByName(genre.getName());
            if(foundGenre != null) {
                throw new ValidateServiceException("El género ya esta registrado!");
            }

            if(genre.getId() == null) {

                return genreRepository.save(genre);
            }

            Genre savedGenre = genreRepository.findById(genre.getId()).orElseThrow(()-> new NoDataFoundException("No existe el género!"));
            savedGenre.setName(genre.getName());
            genreRepository.save(genre);

            return savedGenre;

        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

    @Transactional
    public void delete(Integer id) {
        try{
            Genre genre = genreRepository.findById(id).orElseThrow(()->new NoDataFoundException("El género no existe!"));
            List<Movie> movies = movieRepository.findByGenre(genre);
            for (Movie movie:movies) {
                movieRepository.delete(movie);
            }
            genreRepository.delete(genre);
        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

}
