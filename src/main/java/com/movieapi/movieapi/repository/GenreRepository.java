package com.movieapi.movieapi.repository;

import com.movieapi.movieapi.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer> {
    Genre findByName(String name);
}
