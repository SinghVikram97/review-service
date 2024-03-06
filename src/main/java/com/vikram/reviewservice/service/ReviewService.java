package com.vikram.reviewservice.service;

import com.vikram.reviewservice.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO getReview(Long id);

    List<ReviewDTO> getAllReviews();

    ReviewDTO addReview(ReviewDTO reviewDto);

    ReviewDTO updateReview(Long id, ReviewDTO reviewDto);

    ReviewDTO deleteReview(Long id);

    List<ReviewDTO> getReviewsByCompanyId(Long companyId);

    void deleteReviewsForCompany(Long companyId);
}
