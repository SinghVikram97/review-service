package com.vikram.reviewservice.controller;

import com.vikram.reviewservice.dto.ReviewDTO;
import com.vikram.reviewservice.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ReviewDTO getReview(@PathVariable Long id) {
        logger.info("Get review with id: {} request",id);
        return reviewService.getReview(id);
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        logger.info("Get all reviews request");
        return reviewService.getAllReviews();
    }

    @PostMapping
    public ReviewDTO postReview(@RequestBody @Valid ReviewDTO reviewDto) {
        logger.info("Create review request with request body: {}",reviewDto);
        return reviewService.addReview(reviewDto);
    }

    @DeleteMapping("/{id}")
    public ReviewDTO deleteReview(@PathVariable Long id){
        logger.info("Delete review request with id: {}",id);
        return reviewService.deleteReview(id);
    }

    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody @Valid ReviewDTO reviewDto){
        logger.info("Update review request with id: {} and request body: {}",id,reviewDto);
        return reviewService.updateReview(id, reviewDto);
    }

    @GetMapping("/company")
    public ResponseEntity<List<ReviewDTO>> getAllReviewsByCompanyId(@RequestParam("company_id") Long companyId){
        logger.info("Get all reviews for company with id: {} request",companyId);
        List<ReviewDTO> reviewsByCompanyId = reviewService.getReviewsByCompanyId(companyId);
        return new ResponseEntity<>(reviewsByCompanyId, HttpStatus.OK);
    }
}