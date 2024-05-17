package org.example.controller;

import org.example.model.entity.Episode;
import org.example.model.entity.Series;
import org.example.service.Service;
import org.example.service.impl.SeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
public class SeriesController extends AbstractController<Series> {
    private final SeriesServiceImpl seriesService;

    @Autowired
    public SeriesController(SeriesServiceImpl seriesService) {
        this.seriesService = seriesService;
    }
    @PostMapping("/{seriesId}/add_episodes")
    public ResponseEntity<String> addEpisode(@PathVariable Long seriesId, @RequestBody Episode episode) {
        Series series = seriesService.read(seriesId);
        if (series == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        episode.setSeries(series);
        series.getEpisodes().add(episode);
        seriesService.save(series);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public Service<Series> getService() {
        return seriesService;
    }

}
