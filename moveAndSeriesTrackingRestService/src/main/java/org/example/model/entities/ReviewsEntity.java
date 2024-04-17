package org.example.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "Reviews")
public class ReviewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewID;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "MovieID", nullable = false)
    private MovieAndSeriesEntity movieAndSeries;

    @Column(name = "Rating")
    private float rating;

    @Column(name = "ReviewText")
    private String reviewText;

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public MovieAndSeriesEntity getMovieAndSeries() {
        return movieAndSeries;
    }

    public void setMovieAndSeries(MovieAndSeriesEntity movieAndSeries) {
        this.movieAndSeries = movieAndSeries;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}