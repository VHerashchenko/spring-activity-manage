package com.vh.activitymanage.model.converter;

import com.vh.activitymanage.model.dto.ActivityUserDTO;
import com.vh.activitymanage.model.dto.UserDTO;
import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.service.CategoryService;
import com.vh.activitymanage.service.UserService;
import com.vh.activitymanage.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityUserDtoToInstanceConverter implements Converter<ActivityUserDTO, Activity> {
    private final ModelMapper mapper;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    @Transactional
    public Activity convert(ActivityUserDTO activityUserDTO) {

        Activity activity = mapper.map(activityUserDTO, Activity.class);

        activity.setUsers(
                Arrays.stream(activityUserDTO.getUsersId())
                        .map(x -> userService.findById((long) x))
                        .collect(Collectors.toSet()));

        activity.setCategory(categoryService.getCategoryById(activityUserDTO.getCategory().getId()));

        return activity;
    }
}