package com.movieapi.movieapi.converters;

import com.movieapi.movieapi.dtos.GenreDTO;
import com.movieapi.movieapi.dtos.MovieDTO;
import com.movieapi.movieapi.dtos.UserDTO;
import com.movieapi.movieapi.entities.Genre;
import com.movieapi.movieapi.entities.Movie;
import com.movieapi.movieapi.entities.User;

public class MovieConverter extends AbstractConverter<Movie, MovieDTO>{

    private GenreConverter genreConverter = new GenreConverter();
    private UserConverter userConverter = new UserConverter();
    @Override
    public MovieDTO fromEntity(Movie entity) {
        if(entity == null) return null;

        GenreDTO genreDTO = genreConverter.fromEntity(entity.getGenre());
        UserDTO userDTO = userConverter.fromEntity(entity.getUser());
        return  MovieDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .genre(genreDTO)
                .director(entity.getDirector())
                .yearOfRelease(entity.getYearOfRelease())
                .description(entity.getDescription())
                .duration(entity.getDuration())
                .stars(entity.getStars())
                .isWatched(entity.getIsWatched())
                .user(userDTO)
                .build();

    }

    @Override
    public Movie fromDTO(MovieDTO dto) {
        if(dto == null) return null;

        Genre genre = genreConverter.fromDTO(dto.getGenre());
        User user = userConverter.fromDTO(dto.getUser());
        return  Movie.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .genre(genre)
                .director(dto.getDirector())
                .yearOfRelease(dto.getYearOfRelease())
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .stars(dto.getStars())
                .isWatched(dto.getIsWatched())
                .user(user)
                .build();
    }
}
