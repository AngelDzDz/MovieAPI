package com.movieapi.movieapi.config;

import com.movieapi.movieapi.converters.GenreConverter;
import com.movieapi.movieapi.converters.MovieConverter;
import com.movieapi.movieapi.converters.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Permite el uso de convertidores mediante inyección de dependencias
@Configuration
@ComponentScan("controllers")
public class ConverterConfig {
    @Bean
    public GenreConverter getGenreConverter() {
        return new GenreConverter();
    }

    @Bean
    public MovieConverter getMovieConverter() {
        return new MovieConverter();
    }

    @Bean
    public UserConverter getUserConverter(){return new UserConverter();}

}
