package com.vikram.reviewservice.controller;

import com.vikram.reviewservice.dto.ReviewDTO;
import com.vikram.reviewservice.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ReviewDTO getReview(@PathVariable Long id) {
        return reviewService.getReview(id);
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping
    public ReviewDTO postReview(@RequestBody @Valid ReviewDTO reviewDto) {
        return reviewService.addReview(reviewDto);
    }

    @DeleteMapping("/{id}")
    public ReviewDTO deleteReview(@PathVariable Long id){
        return reviewService.deleteReview(id);
    }

    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody @Valid ReviewDTO reviewDto){
        return reviewService.updateReview(id, reviewDto);
    }
}