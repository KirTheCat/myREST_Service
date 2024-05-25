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
    @JsonBackReference("media-review")
    private Media media;

    @OneToOne
    @JsonBackReference
    private User author;

    private String authorInfo;
    public String printAuthor(User author){
        return "Пользователь: " + author.getUsername() + "; Почта: " + author.getEmail();
    }

    public void setAuthorInfo() {
        this.authorInfo = printAuthor(author);
    }
}
