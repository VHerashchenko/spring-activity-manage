package com.vh.activitymanage.repository;

import com.vh.activitymanage.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Transactional
    void deleteCategoryById(Long id);
}