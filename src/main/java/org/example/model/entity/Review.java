package org.example.model.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.example.model.enums.RatingEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review extends AbstractEntity{
    @Column(name = "text")
    private String text;

    @Column(name = "rating")
    @Enumerated(EnumType.ORDINAL)
    private RatingEnum rating;

    @ManyToOne
    @JoinColumn(name = "media_id")
    @JsonBackReference
    private Media media;

    @OneToOne
    private User author;
}
