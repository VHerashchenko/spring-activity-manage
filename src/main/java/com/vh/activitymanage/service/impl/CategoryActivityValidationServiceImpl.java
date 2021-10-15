package com.vh.activitymanage.service.impl;

import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.repository.ActivityAdminRepository;
import com.vh.activitymanage.service.CategoryActivityValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryActivityValidationServiceImpl implements CategoryActivityValidationService {
    private final ActivityAdminRepository activityAdminRepository;

    @Autowired
    public CategoryActivityValidationServiceImpl(ActivityAdminRepository activityAdminRepository){
        this.activityAdminRepository = activityAdminRepository;
    }

    @Override
    public List<Activity> findRelationWithCategoryById(Long id) {
        return activityAdminRepository.findAllByCategory_Id(id);
    }
}
