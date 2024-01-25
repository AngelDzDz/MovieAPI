package com.movieapi.movieapi.controllers;

import com.movieapi.movieapi.converters.MovieConverter;
import com.movieapi.movieapi.dtos.MovieDTO;
import com.movieapi.movieapi.entities.Movie;
import com.movieapi.movieapi.services.MovieService;
import com.movieapi.movieapi.utils.WrapperResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Movie")
@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieConverter movieConverter;
    @Operation(summary="Retrieve all existing movies of logged user",description="Returns all the movies of logged user")
    @GetMapping(value = "/movies")

    public ResponseEntity<WrapperResponse<List<MovieDTO>>> findAll() {
        List<Movie> movieList = movieService.findAll();
        List<MovieDTO> movieDTOS = movieConverter.fromEntity(movieList);

        return new WrapperResponse<List<MovieDTO>>(true,"success",movieDTOS).createResponse(HttpStatus.OK);
    }

    @Operation(summary="Retrieve an existing movie given it's id",description="Return a movie based on it's id")
    @GetMapping(value="/movies/{id}")
    public ResponseEntity<WrapperResponse<MovieDTO>> findById(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);
        MovieDTO movieDTO = movieConverter.fromEntity(movie);

        return new WrapperResponse<MovieDTO>(true,"success",movieDTO).createResponse(HttpStatus.OK);
    }
    //change operation wording
    @Operation(summary="Retrieve all the unwatched movies of logged user",description="Returns all the movies that are pending to watch of the user")
    @GetMapping("movies/to-watch")
    public ResponseEntity<WrapperResponse<List<MovieDTO>>> findMoviesToWatchByUserId() {
        List<Movie> moviesByUser = movieService.findToWatchMovies();
        return new WrapperResponse<>(true,"success",movieConverter.fromEntity(moviesByUser)).createResponse(HttpStatus.OK);
    }

    @Operation(summary="Retrieve all watched movies of logged user",description="Returns all watched movies of logged user")
    @GetMapping("movies/watched")
    public ResponseEntity<WrapperResponse<List<MovieDTO>>> findMoviesWatchedByUserId() {
        List<Movie> moviesByUser = movieService.findWatchedMovies();
        return new WrapperResponse<>(true,"success",movieConverter.fromEntity(moviesByUser)).createResponse(HttpStatus.OK);
    }

    @Operation(summary="Allow the logged user to create a movie",description="Allow the logged user to create a movie in the database")
    @PostMapping(value="/movies")
    public ResponseEntity<WrapperResponse<MovieDTO>> create(@RequestBody MovieDTO movie) {
        Movie movieCreated = movieService.save(movieConverter.fromDTO(movie));
        MovieDTO movieDTO = movieConverter.fromEntity(movieCreated);

        return new WrapperResponse<MovieDTO>(true,"success",movieDTO).createResponse(HttpStatus.CREATED);
    }

    @Operation(summary="Allow the logged user to create an unwatched movie",description="Allow the logged user to create an unwatched movie in the database")
    @PostMapping("movies/to-watch")
    public ResponseEntity<WrapperResponse<MovieDTO>> createMovieToWatch(@RequestBody MovieDTO movie) {
        Movie movieCreated = movieService.saveIsWatched(movieConverter.fromDTO(movie),false);
        return new WrapperResponse<>(true,"success",movieConverter.fromEntity(movieCreated)).createResponse(HttpStatus.OK);
    }

    @Operation(summary="Allow the logged user to create a watched movie",description="Allow the logged user to create a watched movie in the database")
    @PostMapping("movies/watched")
    public ResponseEntity<WrapperResponse<MovieDTO>> createMovieWatched(@RequestBody MovieDTO movie) {
        Movie movieCreated = movieService.saveIsWatched(movieConverter.fromDTO(movie),true);
        return new WrapperResponse<>(true,"success",movieConverter.fromEntity(movieCreated)).createResponse(HttpStatus.OK);
    }

    @Operation(summary="Allow the logged user to update a movie",description="Allow the logged user to update a movie on the database")
    @PutMapping(value="/movies")
    public ResponseEntity<WrapperResponse<MovieDTO>> update(@RequestBody MovieDTO movie) {
        Movie updatedMovie = movieService.save(movieConverter.fromDTO(movie));
        MovieDTO movieDTO = movieConverter.fromEntity(updatedMovie);

        return new WrapperResponse<MovieDTO>(true,"success",movieDTO).createResponse(HttpStatus.OK);
    }

    @Operation(summary="Allow the logged user to delete a movie",description="Allow the logged user to delete a movie from the database")
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        movieService.delete(id);
        return new WrapperResponse<>(true,"success",null).createResponse(HttpStatus.OK);
    }

}
