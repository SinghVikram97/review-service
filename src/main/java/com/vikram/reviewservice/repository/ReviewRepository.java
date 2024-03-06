package com.vikram.reviewservice.repository;

import com.vikram.reviewservice.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> getReviewEntityByCompanyId(Long companyId);
    void deleteByCompanyId(Long companyId);
}
