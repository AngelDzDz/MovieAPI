package com.movieapi.movieapi.converters;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractConverter<E,D> {

    public abstract D fromEntity(E entity);
    public abstract E fromDTO(D dto);

    public List<D> fromEntity(List<E> entities) {
        List<D> dtoList = new ArrayList<>();

        for (E entity: entities) {
            dtoList.add(fromEntity(entity));
        }

        return dtoList;
    }

    public List<E> fromDTO(List<D> dtos) {
        List<E> entitiesList = new ArrayList<>();

        for (D dto: dtos) {
            entitiesList.add(fromDTO(dto));

        }
        return entitiesList;
    }



}
