package com.vh.activitymanage.validator;

import com.vh.activitymanage.service.CategoryActivityValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryRelationValidator {
    CategoryActivityValidationService categoryActivityValidationService;

    @Autowired
    public CategoryRelationValidator(CategoryActivityValidationService categoryActivityValidationService){
        this.categoryActivityValidationService = categoryActivityValidationService;
    }

    public boolean validate(Long id) {
        return !categoryActivityValidationService.findRelationWithCategoryById(id).isEmpty();
    }
}