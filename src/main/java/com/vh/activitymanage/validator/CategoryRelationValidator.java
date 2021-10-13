package com.vh.activitymanage.validator;

import com.vh.activitymanage.repository.ActivityCategoryValidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryRelationValidator {
    ActivityCategoryValidateRepository activityRepository;

    @Autowired
    public CategoryRelationValidator(ActivityCategoryValidateRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    public boolean validate(Long id) {
        return !activityRepository.findAllByCategory_Id(id).isEmpty();
    }
}