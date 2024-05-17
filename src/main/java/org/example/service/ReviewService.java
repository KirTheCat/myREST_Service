package org.example.service;

import org.example.model.entity.Review;

public interface ReviewService extends Service<Review> {

    public Review createAndReturn(Review entity);

}
