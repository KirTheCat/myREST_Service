package org.example.model.repository;

import org.example.model.entities.MovieAndSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MovieAndSeriesRepository  extends JpaRepository<MovieAndSeriesEntity, Integer>{
}
