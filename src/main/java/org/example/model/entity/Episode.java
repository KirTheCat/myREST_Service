package org.example.model.entity;


import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Getter
@Setter
@Entity
@Table(name = "episodes")
public class Episode extends AbstractEntity{
    @Column(name = "episode_number")
    private int episodeNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "release_date")
    private String releaseDate;

    @ManyToOne
    @JoinColumn(name = "series_id")
    @JsonBackReference
    private Series series;
}