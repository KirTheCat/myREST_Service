package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class Media {
    private int mediaId;
    private String title;
    private String genre;
    private String director;
    private List<String> actors;
    private Date releaseDate;
    private List<Review> reviews;

}