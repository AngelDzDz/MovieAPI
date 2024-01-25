package com.movieapi.movieapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name="MOVIES")
public class Movie {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AI
    private Long id;

    @Column(name="TITLE", nullable=false,length=100)
    private String title;

    @ManyToOne//(cascade=CascadeType.ALL)
    @JoinColumn(name="FK_GENRE", nullable = false)
    private Genre genre;

    @Column(name="DIRECTOR",nullable = false,length=100)
    private String director;

    @Column(name="YEAROFRELEASE",nullable = false)
    private Integer yearOfRelease;

    @Column(name="DESCRIPTION",nullable = false,length=100)
    private String description;

    @Column(name="DURATION",nullable = false)
    private Integer duration;

    @Column(name="STARS")
    private Integer stars;

    @Column(name="ISWATCHED",nullable = false)
    private Boolean isWatched;

    @ManyToOne
    @JoinColumn(name="FK_USER",nullable = false,updatable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
