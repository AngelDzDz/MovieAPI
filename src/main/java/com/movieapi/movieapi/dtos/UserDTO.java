package com.movieapi.movieapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Schema(name="User",description = "Represents the user")
public class UserDTO {
    @Schema(description="Unique Id of the user",example = "2")
    private Long id;

    @NotNull
    @NotBlank
    @Schema(description="Username of the user",example = "Pedro")
    private String username;
}
