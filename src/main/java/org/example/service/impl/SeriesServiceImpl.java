package org.example.service.impl;

import org.example.model.entity.Series;
import org.example.repository.SeriesRepository;
import org.example.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {
    @Autowired
    private SeriesRepository seriesRepository;

    @Override
    public Series read(Long id) {
        return seriesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Series> readAll() {
        return seriesRepository.findAll();
    }

    @Override
    public Series save(Series entity) {
        return seriesRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        seriesRepository.deleteById(id);
    }

    @Override
    public Series create(Series entity) {
        return seriesRepository.save(entity);
    }
}
