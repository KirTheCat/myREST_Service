package org.example.model.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie extends Media {
    @Column(name = "duration_in_minutes")
    private int durationInMinutes;
}
