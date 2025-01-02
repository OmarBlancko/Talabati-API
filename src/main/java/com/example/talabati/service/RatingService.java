package com.example.talabati.service;

import org.springframework.stereotype.Service;
import com.example.talabati.repositories.RatingRepository;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }
}
