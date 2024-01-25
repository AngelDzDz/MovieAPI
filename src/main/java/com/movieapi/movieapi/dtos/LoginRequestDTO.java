package com.movieapi.movieapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "Represents request of the login")
public class LoginRequestDTO {
    @Schema(description="Username of the user")
    private String username;

    @Schema(description="Pasword of the user")
    private String password;
}
