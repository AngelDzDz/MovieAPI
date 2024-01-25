package com.movieapi.movieapi.controllers;

import com.movieapi.movieapi.converters.GenreConverter;
import com.movieapi.movieapi.dtos.GenreDTO;
import com.movieapi.movieapi.entities.Genre;
import com.movieapi.movieapi.services.GenreService;
import com.movieapi.movieapi.utils.WrapperResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Genre")
@RestController
public class GenreController {
    @Autowired
    private GenreService genreService;
    @Autowired
    private GenreConverter genreConverter;

    @Operation(summary="Retrieve all the movie genres",description="Returns all the movie genres")
    @GetMapping(value = "/genres")

    public ResponseEntity<WrapperResponse<List<GenreDTO>>> findAll() {
        List<Genre> genres = genreService.findAll();
        return new WrapperResponse<>(true,"success",genreConverter.fromEntity(genres)).createResponse(HttpStatus.OK);
    }

    @Operation(summary="Retrieve a movie genre",description="Returns a movie genre given it's a id")
    @GetMapping(value = "/genres/{id}")
    public ResponseEntity<WrapperResponse<GenreDTO>> findById(@PathVariable(name="id") Integer id) {
        Genre genre = genreService.findById(id);
        return new WrapperResponse<>(true,"success",genreConverter.fromEntity(genre)).createResponse(HttpStatus.OK);
    }

    @Operation(summary="Creates a movie genre",description="Creates a movie genre on the database")
    @PostMapping(value = "/genres")
    public ResponseEntity<WrapperResponse<GenreDTO>> create(@RequestBody GenreDTO genre) {
       Genre genreCreated = genreService.save(genreConverter.fromDTO(genre));
        return new WrapperResponse<>(true,"success",genreConverter.fromEntity(genreCreated)).createResponse(HttpStatus.CREATED);
    }

    @Operation(summary="Updates a movie genre",description="Updated a movie genre on the database")
    @PutMapping(value = "/genres")
    public ResponseEntity<WrapperResponse<GenreDTO>> update(@RequestBody GenreDTO genre) {
        Genre genreCreated = genreService.save(genreConverter.fromDTO(genre));
        return new WrapperResponse<>(true,"success",genreConverter.fromEntity(genreCreated)).createResponse(HttpStatus.OK);
    }

    @Operation(summary="Deletes a movie genre",description="Deletes a movie genre on the database based on it's id")
    @DeleteMapping(value = "/genres/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id ){
        genreService.delete(id);
        return new WrapperResponse<>(true,"success",null).createResponse(HttpStatus.OK);
    }
}
