package com.vikram.reviewservice.service;

import com.vikram.reviewservice.delegate.CompanyServiceDelegate;
import com.vikram.reviewservice.dto.ReviewDTO;
import com.vikram.reviewservice.entity.ReviewEntity;
import com.vikram.reviewservice.exception.ResourceNotFoundException;
import com.vikram.reviewservice.mapper.ModelMapper;
import com.vikram.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final CompanyServiceDelegate companyServiceDelegate;

    @Override
    public ReviewDTO getReview(Long id) {
        ReviewEntity review = getReviewOrThrowException(id);
        return modelMapper.mapReviewToReviewDto(review);
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        List<ReviewEntity> reviewList = reviewRepository.findAll();
        return reviewList.stream().map(modelMapper::mapReviewToReviewDto).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO addReview(ReviewDTO reviewDto) {
        Long companyId = reviewDto.getCompanyId();
        // validate companyId
        companyServiceDelegate.getCompanyById(companyId);

        // If valid company
        ReviewEntity review = modelMapper.mapReviewDtoToReview(reviewDto);
        ReviewEntity savedReview = reviewRepository.save(review);
        return modelMapper.mapReviewToReviewDto(savedReview);
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDto) {
        ReviewEntity review = getReviewOrThrowException(id);

        // validate company
        Long companyId = reviewDto.getCompanyId();
        companyServiceDelegate.getCompanyById(companyId);

        // if valid company
        review.setTitle(reviewDto.getTitle());
        review.setDescription(reviewDto.getDescription());
        review.setRating(reviewDto.getRating());
        review.setCompanyId(review.getCompanyId());

        ReviewEntity savedReview = reviewRepository.save(review);

        return modelMapper.mapReviewToReviewDto(savedReview);
    }

    @Override
    public ReviewDTO deleteReview(Long id) {
        ReviewEntity review = getReviewOrThrowException(id);
        reviewRepository.deleteById(review.getId());
        return modelMapper.mapReviewToReviewDto(review);
    }

    @Override
    public List<ReviewDTO> getReviewsByCompanyId(Long companyId) {
        List<ReviewEntity> reviewEntityList = reviewRepository.getReviewEntityByCompanyId(companyId);
        return reviewEntityList.stream().map(modelMapper::mapReviewToReviewDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteReviewsForCompany(Long companyId) {
        reviewRepository.deleteByCompanyId(companyId);
    }

    private ReviewEntity getReviewOrThrowException(Long id){
        return reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review", "Id", id));
    }
}

