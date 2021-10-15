package com.vh.activitymanage.controller;

import com.vh.activitymanage.model.dto.ActivityUserDTO;
import com.vh.activitymanage.model.enums.ActivityStatus;
import com.vh.activitymanage.service.AdminActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PreAuthorize("hasAuthority('write')")
@RequestMapping("/admin/activity")
public class AdminActivityController {
    private static final Type ACTIVITY_USER_LIST_TYPE = (new TypeToken<List<ActivityUserDTO>>(){}).getType();

    private final AdminActivityService adminActivityService;
    private final ModelMapper mapper;

    @GetMapping("/confirm")
    public String getAllUnconfirmedActivities(Model model){
        log.debug("GetDashboard");

        var allActivities = adminActivityService.findAllWaitingActivities();

        var attributes = Map.of(
                "activityWaitToActive", mapper.map(allActivities.get("waitActive"), ACTIVITY_USER_LIST_TYPE),
                "activityWaitToDelete", mapper.map(allActivities.get("waitDelete"), ACTIVITY_USER_LIST_TYPE));

        model.addAllAttributes(attributes);

        return "admin/confirmActivities";
    }

    @GetMapping("/all")
    public String getAllActivities(Model model, String sort){
        log.debug("GetDashboard");

        var allActivities = adminActivityService.findAllActivities(sort);
        model.addAttribute("activityWaitToActive", mapper.map(allActivities, ACTIVITY_USER_LIST_TYPE));

        return "admin/allActiveActivities";
    }

    @PostMapping("/confirm/{id}/active")
    public String updateActivityStatus(@PathVariable Long id){
        log.debug("Update Activity ACTIVE");

        adminActivityService.updateActivityStatusById(id, ActivityStatus.ACTIVE);

        return "redirect:/admin/activity/confirm";
    }

    @DeleteMapping("/confirm/{id}/delete")
    public String deleteActivity(@PathVariable Long id){
        log.debug("deleteActivity");

        adminActivityService.deleteActivityById(id);

        return "redirect:/admin/activity/confirm";
    }
}