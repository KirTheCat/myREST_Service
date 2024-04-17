package org.example.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "MoviesAndSeries")
public class MovieAndSeriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieID;

    @Column(name = "Title")
    private String title;

    @Column(name = "Genre")
    private String genre;

    @Column(name = "IsSeries")
    private boolean isSeries;

    @Column(name = "TotalEpisodes")
    private int totalEpisodes;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean getIsSeries() {
        return isSeries;
    }

    public void setIsSeries(boolean isSeries) {
        this.isSeries = isSeries;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }
}