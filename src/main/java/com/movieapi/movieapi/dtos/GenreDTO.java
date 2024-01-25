package com.movieapi.movieapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(name="Genre", description = "Represents the genre of the movie")
public class GenreDTO {
    @Schema(description="Unique Id of the genre",example = "43")
    private Integer id;

    @NotNull
    @NotBlank
    @Schema(description="Name of the genre",example = "Suspense")
    private String name;
}
