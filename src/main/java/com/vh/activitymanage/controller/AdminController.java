package com.vh.activitymanage.controller;

import com.vh.activitymanage.model.dto.ActivityUserDTO;
import com.vh.activitymanage.model.dto.UserDTO;
import com.vh.activitymanage.model.enums.ActivityStatus;
import com.vh.activitymanage.model.enums.UserStatus;
import com.vh.activitymanage.service.AdminService;
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
@RequestMapping("/admin")
public class AdminController {
    private static final Type ACTIVITY_USER_LIST_TYPE = (new TypeToken<List<ActivityUserDTO>>(){}).getType();
    private static final Type USER_LIST_TYPE = (new TypeToken<List<UserDTO>>(){}).getType();

    private final AdminService adminService;
    private final ModelMapper mapper;

    @GetMapping("/dashboard")
    public String getAllTables(Model model){
        log.debug("GetDashboard");

        var activeUsers = adminService.findAllActiveUsers();
        var bannedUsers = adminService.findAllBannedUsers();

        var activityWaitToActive = adminService.findAllWaitToActive();
        var activityWaitToDelete = adminService.findAllWaitToDelete();

        var attributes = Map.of("activeUsers", mapper.map(activeUsers, USER_LIST_TYPE),
                    "bannedUsers", mapper.map(bannedUsers, USER_LIST_TYPE),
                    "activityWaitToActive", mapper.map(activityWaitToActive, ACTIVITY_USER_LIST_TYPE),
                    "activityWaitToDelete", mapper.map(activityWaitToDelete, ACTIVITY_USER_LIST_TYPE));

        model.addAllAttributes(attributes);

        return "admin/dashboard";
    }

    @PostMapping("/dashboard/{id}/unban")
    public String updateUserStatusToActive(@PathVariable Long id){
        log.debug("UpdateUserTo ACTIVE");

        adminService.updateUserStatus(id, UserStatus.ACTIVE);

        return "redirect:/admin/dashboard";
    }

    @PostMapping("/dashboard/{id}/ban")
    public String updateUserStatusToBanned(@PathVariable Long id){
        log.debug("UpdateUserTo BANNED");

        adminService.updateUserStatus(id, UserStatus.BANNED);

        return "redirect:/admin/dashboard";
    }

    @PostMapping("/dashboard/{id}/active")
    public String updateActivityStatus(@PathVariable Long id){
        log.debug("Update Activity ACTIVE");

        adminService.updateActivityStatusById(id, ActivityStatus.ACTIVE);

        return "redirect:/admin/dashboard";
    }

    @DeleteMapping("/dashboard/{id}/delete")
    public String deleteActivity(@PathVariable Long id){
        log.debug("deleteActivity");

        adminService.deleteActivityById(id);

        return "redirect:/admin/dashboard";
    }
}