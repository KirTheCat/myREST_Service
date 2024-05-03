package org.example.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Media extends AbstractEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "director")
    private String director;

    @Column(name = "release_year")
    private int releaseYear;

    @OneToMany(mappedBy = "media")
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}