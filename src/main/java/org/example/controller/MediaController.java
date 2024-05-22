package org.example.controller;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.example.model.entity.*;
import org.example.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.service.impl.MediaServiceImpl;
import org.example.service.impl.MovieServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.example.service.impl.ReviewServiceImpl;
import org.example.service.impl.SeriesServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

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
    @Override
    @PostMapping("/mediaEntity")
    public ResponseEntity<String> post(@RequestBody Media entity) {
        return super.post(entity);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    ///// добавить отзыв авторизованным пользователем
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/{mediaId}/add_review")
    public ResponseEntity<String> post(@PathVariable Long mediaId, @RequestBody Review entity, @AuthenticationPrincipal User user) {
        Media media = mediaService.read(mediaId);
        if (media == null) {
            return new ResponseEntity<>("Ошибка! Объект с таким id не найден!", HttpStatus.NOT_FOUND);
        }
        entity.setMedia(media);
        entity.setAuthor(user);
        entity.setAuthorInfo();
        Review savedReview = reviewService.createAndReturn(entity);
        media.getReviews().add(savedReview);
        mediaService.save(media);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{mediaId}/reviews")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long mediaId){
        Media media = mediaService.read(mediaId);
        if (media == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Review> reviews = new ArrayList<>();
        reviews.addAll(media.getReviews());

        return new ResponseEntity<>(reviews, headers, HttpStatus.OK);
    }
    //////////// для сериала

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{mediaId}/add_ep")
    public ResponseEntity<String> addEpisode(@PathVariable Long mediaId, @RequestBody Map<String, Object> specificObject) {
        Media media = mediaService.read(mediaId);
        if (media == null) {
            return new ResponseEntity<>("Ошибка! Объект с таким ID не найден!", HttpStatus.NOT_FOUND);
        }
        if (media instanceof Series) {
            Episode episode = new ObjectMapper().convertValue(specificObject, Episode.class);
            episode.setSeries((Series) media);
            ((Series) media).getEpisodes().add(episode);
            seriesService.save((Series) media);
        } else return new ResponseEntity<>("Ошибка! Объект не является сериалом!", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public Service<Media> getService() {
        return mediaService;
    }
}