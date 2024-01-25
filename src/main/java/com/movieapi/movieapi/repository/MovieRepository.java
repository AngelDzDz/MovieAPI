package com.movieapi.movieapi.repository;

import com.movieapi.movieapi.entities.Genre;
import com.movieapi.movieapi.entities.Movie;
import com.movieapi.movieapi.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByGenre(Genre genre);
    List<Movie> findByUser(User user);
    List <Movie>findByUserIdAndIsWatched(Long userId, boolean isWatched);

}
