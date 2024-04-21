package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Episode {
    private String seriesName;
    private int episodeNumber;
    private String title;
    private Date releaseDate;

}