package com.vh.activitymanage.validator;

import com.vh.activitymanage.repository.ActivityAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryRelationValidator {
    ActivityAdminRepository activityRepository;

    @Autowired
    public CategoryRelationValidator(ActivityAdminRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    public boolean validate(Long id) {
        return !activityRepository.findAllByCategory_Id(id).isEmpty();
    }
}