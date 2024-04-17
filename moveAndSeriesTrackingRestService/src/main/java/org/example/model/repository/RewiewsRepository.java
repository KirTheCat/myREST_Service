package org.example.model.repository;

import org.example.model.entities.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RewiewsRepository extends JpaRepository<ReviewsEntity, Integer> {
}
