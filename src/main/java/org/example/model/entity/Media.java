package org.example.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Getter
@Setter
@Entity
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Movie.class, name = "movie"),
        @JsonSubTypes.Type(value = Series.class, name = "series")
})
public abstract class Media extends AbstractEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "director")
    private String director;

    @Column(name = "release_year")
    private int releaseYear;

    @OneToMany
    private List<Review> reviews;

}