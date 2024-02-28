package com.vikram.reviewservice.mapper;

import com.vikram.reviewservice.dto.ReviewDTO;
import com.vikram.reviewservice.entity.ReviewEntity;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class ModelMapper {
    public ReviewDTO mapReviewToReviewDto(ReviewEntity review){
        return ReviewDTO.builder()
                .title(review.getTitle())
                .description(review.getDescription())
                .rating(review.getRating())
                .companyId(review.getCompanyId())
                .id(review.getId())
                .build();
    }
    public ReviewEntity mapReviewDtoToReview(ReviewDTO reviewDto){
        return ReviewEntity.builder()
                .title(reviewDto.getTitle())
                .description(reviewDto.getDescription())
                .rating(reviewDto.getRating())
                .companyId(reviewDto.getCompanyId())
                .build();
    }
}
