package org.example.service.impl;

import org.example.model.entity.Review;
import org.example.repository.ReviewRepository;
import org.example.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review read(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }
    @Override
    public List<Review> readAll(){
        return reviewRepository.findAll();
    }
    @Override
    public Review save(Review entity) {
        return reviewRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
    @Override
    public long count() {
        return reviewRepository.count();
    }
    @Override
    public Review create(Review entity){
        return reviewRepository.save(entity);
    }
    @Override
    public Review createAndReturn(Review entity){
        return reviewRepository.save(entity);
    }

}
