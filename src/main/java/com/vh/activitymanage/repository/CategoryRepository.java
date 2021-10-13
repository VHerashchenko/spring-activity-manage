package com.vh.activitymanage.repository;

import com.vh.activitymanage.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}