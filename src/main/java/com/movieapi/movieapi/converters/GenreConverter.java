package com.movieapi.movieapi.converters;

import com.movieapi.movieapi.dtos.GenreDTO;
import com.movieapi.movieapi.entities.Genre;

public class GenreConverter extends AbstractConverter<Genre, GenreDTO>{
    @Override
    public GenreDTO fromEntity(Genre entity) {
        if(entity == null)  return null;

        return GenreDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
    @Override
    public Genre fromDTO(GenreDTO dto) {
        if(dto == null) return null;

        return Genre.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
