package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.entity.Media;
import org.example.model.entity.Movie;
import org.example.model.entity.Review;
import org.example.model.entity.Series;
import org.example.service.Service;
import org.example.service.impl.MediaServiceImpl;
import org.example.service.impl.MovieServiceImpl;
import org.example.service.impl.ReviewServiceImpl;
import org.example.service.impl.SeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/media")
public class MediaController extends AbstractController<Media> {
    private final MediaServiceImpl mediaService;
    private final MovieServiceImpl movieService;
    private final SeriesServiceImpl seriesService;

    private final ReviewServiceImpl reviewService;

    @Autowired
    public MediaController(MediaServiceImpl mediaService,MovieServiceImpl movieService,SeriesServiceImpl seriesService,ReviewServiceImpl reviewService) {
        this.mediaService = mediaService;
        this.movieService = movieService;
        this.seriesService = seriesService;
        this.reviewService = reviewService;
    }

    //////////Заглушка/////////////
    @Override
    @PostMapping("/mediaEntity")
    public ResponseEntity<String> post(@RequestBody Media entity) {
        return super.post(entity);
    }
    ///////////////////////////////

    @PostMapping
    public ResponseEntity<String> post(@RequestBody Map<String, Object> entity) {
        String type = (String) entity.get("type");
        if ("movie".equals(type)) {
            Movie movie = new ObjectMapper().convertValue(entity, Movie.class);
            movieService.create(movie);
        } else if ("series".equals(type)) {
            Series series = new ObjectMapper().convertValue(entity, Series.class);
            seriesService.create(series);
        } else {
            return new ResponseEntity<>("Invalid type", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{mediaId}/add_review")
    public ResponseEntity<String> post(@PathVariable Long mediaId, @RequestBody Review entity) {
        Media media = mediaService.read(mediaId);
        if (media == null) {
            return new ResponseEntity<>("Media not found", HttpStatus.NOT_FOUND);
        }
        // Validate the review here...
        entity.setMedia(media); // Set the media for the review
        Review savedReview = reviewService.createAndReturn(entity);
        media.getReviews().add(savedReview);
        mediaService.save(media);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Media>> getAll() {
        List<Media> entities = new ArrayList<>();
        entities.addAll(movieService.readAll());
        entities.addAll(seriesService.readAll());
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entities, headers, HttpStatus.OK);
    }

    @Override
    public Service<Media> getService() {
        return mediaService;
    }
}