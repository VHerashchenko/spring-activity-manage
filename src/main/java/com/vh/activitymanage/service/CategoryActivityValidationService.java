package com.vh.activitymanage.service;

import com.vh.activitymanage.model.entity.Activity;

import java.util.List;

public interface CategoryActivityValidationService {
    List<Activity> findRelationWithCategoryById(Long id);
}
