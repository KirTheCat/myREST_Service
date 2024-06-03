package org.example.service.impl;

import org.example.model.entity.Media;
import org.example.model.entity.Movie;
import org.example.repository.MovieRepository;
import org.example.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public Movie read(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
    @Override
    public List<Movie> readAll(){
        return movieRepository.findAll();
    }
    @Override
    public Movie save(Movie entity) {
        return  movieRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public long count() {
        return movieRepository.count();
    }
    @Override
    public Movie create(Movie entity){
        return movieRepository.save(entity);
    }

}
