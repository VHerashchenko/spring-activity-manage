package com.vh.activitymanage.model.converter;

import com.vh.activitymanage.model.dto.ActivityUserDTO;
import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityUserDtoToInstanceConverter implements Converter<ActivityUserDTO, Activity> {
    private final ModelMapper mapper;
    private final CategoryService categoryService;

    @Override
    public Activity convert(ActivityUserDTO activityUserDTO) {

        Activity activity = mapper.map(activityUserDTO, Activity.class);

        activity.setCategory(categoryService.getCategoryById(activityUserDTO.getCategory().getId()));

        return activity;
    }
}