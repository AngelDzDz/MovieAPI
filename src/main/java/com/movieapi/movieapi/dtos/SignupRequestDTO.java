package com.movieapi.movieapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


@Schema
public class SignupRequestDTO {
    private String username;
    private String password;
}
