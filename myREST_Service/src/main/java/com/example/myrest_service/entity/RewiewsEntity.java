import javax.persistence.*;

@Entity
@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewID;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "MovieID", nullable = false)
    private MovieAndSeries movieAndSeries;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MovieAndSeries getMovieAndSeries() {
        return movieAndSeries;
    }

    public void setMovieAndSeries(MovieAndSeries movieAndSeries) {
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