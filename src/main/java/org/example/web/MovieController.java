package org.example.web;

import org.example.model.entity.Movie;
import org.example.service.Service;
import org.example.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController extends AbstractController{
    private final MovieServiceImpl movieService;
    @Autowired
    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @Override
    public Service<Movie> getService() {
        return movieService;
    }

}
