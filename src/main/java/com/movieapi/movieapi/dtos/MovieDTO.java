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

@Schema(name="Movie",description = "Represents the movie")
public class MovieDTO {
    @Schema(description="Unique Id of the movie",example = "123")
    private Long id;

    @NotNull
    @NotBlank
    @Schema(description="Title of the movie",example = "The dark knight")
    private String title;

    @NotNull
    @NotBlank
    @Schema(description="Genre of the movie. It should exist on the API")
    private GenreDTO genre;

    @NotNull
    @NotBlank
    @Schema(description="Director of the movie",example = "Christopher Nolan")
    private String director;

    @NotNull
    @NotBlank
    @Schema(description="Year of release of the movie",example="2008")
    private Integer yearOfRelease;

    @NotNull
    @NotBlank
    @Schema(description="Description of the movie",example = "One of the best movies in history...")
    private String description;

    @NotNull
    @NotBlank
    @Schema(description="Duration of the movie in minutes",example = "120")
    private Integer duration;

    @Schema(description="Rating of the movie expressed on stars[1-5]",example = "5")
    private Integer stars;

    @NotNull
    @NotBlank
    @Schema(description="Establish if the movie has been watched or not",example = "true")
    private Boolean isWatched;

    @Schema(description="Identifier of the associated user. It shouldn't be indicated " +
            "as this is automatically extracted from the logged user"
            ,example = "120")
    private UserDTO user;
}
