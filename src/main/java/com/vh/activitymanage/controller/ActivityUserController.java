package com.vh.activitymanage.controller;

import com.vh.activitymanage.model.dto.ActivityUserDTO;
import com.vh.activitymanage.model.dto.CategoryDTO;
import com.vh.activitymanage.model.dto.UserDTO;
import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.service.ActivityUserService;
import com.vh.activitymanage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/activity")
public class ActivityUserController {
    private static final Type ACTIVITY_USER_LIST_TYPE = (new TypeToken<List<ActivityUserDTO>>(){}).getType();
    private static final Type CATEGORY_LIST_TYPE = (new TypeToken<List<CategoryDTO>>(){}).getType();

    private final ModelMapper mapper;
    private final ActivityUserService activityUserService;
    private final CategoryService categoryService;
    private final ConversionService conversionService;

    @GetMapping("/create")
    public String createActivity(Model model){
        log.debug("createActivity getMapping start");

        var categories = categoryService.findAll();

        final Map<String, Object> attributes = Map.of(
                "activity", new ActivityUserDTO(new UserDTO(), new CategoryDTO()),
                "categories", mapper.map(categories, CATEGORY_LIST_TYPE));

        model.addAllAttributes(attributes);

        return "activity/activityCreation";
    }

    @PostMapping("/update")
    public String updateActivity(@ModelAttribute("activity") ActivityUserDTO activityUserDTO, BindingResult bindingResult){
        log.debug("createActivity PostMapping");

        var activity = conversionService.convert(activityUserDTO, Activity.class);

        if (bindingResult.hasErrors()) {
            return "activity/activityCreation";
        }

        activityUserService.saveActivity(activity);

        return "redirect:/activity/all";
    }

    @GetMapping("/{id}/edit")
    public String edit(@ModelAttribute("activity") ActivityUserDTO activityUserDTO, @PathVariable Long id, Model model) {
        log.debug("editActivity Post");

        var activity = activityUserService.getActivityById(id);
        var categories = categoryService.findAll();

        final Map<String, Object> attributes = Map.of(
                "activity", mapper.map(activity, ActivityUserDTO.class),
                "categories", mapper.map(categories, CATEGORY_LIST_TYPE));

        model.addAllAttributes(attributes);

        return "activity/activityCreation";
    }

    @PostMapping("/{id}")
    public String deleteRequest(@PathVariable Long id){
        log.debug("deleteRequest");

        activityUserService.deleteActivityRequestById(id);

        return "redirect:/activity/all";
    }

    @GetMapping("/all")
    public String getAllCategories(Model model){
        log.debug("getAllActivities GetMapping");

        var activities = activityUserService.findAll();

        model.addAttribute("activities", mapper.map(activities, ACTIVITY_USER_LIST_TYPE));

        return "activity/allActivities";
    }
}