package com.movieapi.movieapi.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LoginResponseDTO {
    private UserDTO user;
    private String token;
}
