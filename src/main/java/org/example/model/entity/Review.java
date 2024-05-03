package org.example.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.model.enums.RatingEnum;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review extends AbstractEntity{
    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private RatingEnum rating;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;
}
