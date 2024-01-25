package com.movieapi.movieapi.converters;

import com.movieapi.movieapi.dtos.SignupRequestDTO;
import com.movieapi.movieapi.dtos.UserDTO;
import com.movieapi.movieapi.entities.User;

public class UserConverter extends AbstractConverter<User, UserDTO>{
    @Override
    public UserDTO fromEntity(User entity) {
        if(entity == null) return null;
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }

    @Override
    public User fromDTO(UserDTO dto) {
        if(dto == null) return null;

        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .build();
    }

    public User signUp(SignupRequestDTO request) {
        if(request == null) return null;
        return User.builder()
                .id(null)
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }
}
