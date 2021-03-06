package com.vh.activitymanage.controller;

import com.vh.activitymanage.model.dto.ActivityUserDTO;
import com.vh.activitymanage.model.dto.CategoryDTO;
import com.vh.activitymanage.model.dto.UserDTO;
import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.service.ActivityUserService;
import com.vh.activitymanage.service.CategoryService;
import com.vh.activitymanage.service.UserService;
import com.vh.activitymanage.validator.BannedUserValidator;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/activity")
public class ActivityUserController {
    private static final Type ACTIVITY_USER_LIST_TYPE = (new TypeToken<List<ActivityUserDTO>>(){}).getType();
    private static final Type CATEGORY_LIST_TYPE = (new TypeToken<List<CategoryDTO>>(){}).getType();
    private static final Type USER_LIST_TYPE = (new TypeToken<List<UserDTO>>(){}).getType();

    private final ModelMapper mapper;
    private final ActivityUserService activityUserService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ConversionService conversionService;
    private final BannedUserValidator bannedUserValidator;

    @GetMapping("/{id}")
    public String getActivityById(@PathVariable Long id, Model model){
        var activity = activityUserService.getActivityById(id);

        model.addAttribute("activity", mapper.map(activity, ActivityUserDTO.class));

        return "activity/activityPage";
    }

    @GetMapping("/create")
    public String createActivity(Model model){
        log.debug("createActivity getMapping start");

        if(bannedUserValidator.validate()) {
            return "redirect:/activity/all";
        }

        var categories = categoryService.findAll();
        var users = userService.findAllByUsernameNot();

        final Map<String, Object> attributes = Map.of(
                "activity", new ActivityUserDTO(new HashSet<>(), new CategoryDTO()),
                "categories", mapper.map(categories, CATEGORY_LIST_TYPE),
                "users", mapper.map(users, USER_LIST_TYPE));

        model.addAllAttributes(attributes);

        return "activity/activityCreation";
    }

    @PostMapping("/update")
    public String updateActivity(@ModelAttribute("activity") ActivityUserDTO activityUserDTO, BindingResult bindingResult){
        log.debug("createActivity PostMapping");
        if (bindingResult.hasErrors()) {
            return "activity/activityCreation";
        }

        var activity = conversionService.convert(activityUserDTO, Activity.class);

        activityUserService.saveActivity(activity);

        return "redirect:/activity/all";
    }

    @GetMapping("/{id}/edit")
    public String edit(@ModelAttribute("activity") ActivityUserDTO activityUserDTO, @PathVariable Long id, Model model) {
        log.debug("editActivity Post");

        if(bannedUserValidator.validate()) {
            return "redirect:/activity/all";
        }

        var activity = activityUserService.getActivityById(id);
        var categories = categoryService.findAll();
        var users = userService.findAllByUsernameNot();

        final Map<String, Object> attributes = Map.of(
                "activity", mapper.map(activity, ActivityUserDTO.class),
                "categories", mapper.map(categories, CATEGORY_LIST_TYPE),
                "users", mapper.map(users, USER_LIST_TYPE));

        model.addAllAttributes(attributes);

        return "activity/activityCreation";
    }

    @PostMapping("/{id}")
    public String deleteRequest(@PathVariable Long id){
        log.debug("deleteRequest");

        if(bannedUserValidator.validate()) {
            return "redirect:/activity/all";
        }

        activityUserService.deleteActivityRequestById(id);

        return "redirect:/activity/all";
    }

    @GetMapping("/all")
    public String getAllActivities(Model model, String sort){
        log.debug("getAllActivities GetMapping");

        if(bannedUserValidator.validate()) {
            model.addAttribute("errorMessage", "banned.user.message");
        }

        var activities = activityUserService.findAllWithCurrentUser(sort);

        model.addAttribute("activities", mapper.map(activities, ACTIVITY_USER_LIST_TYPE));

        return "activity/allActivities";
    }
}