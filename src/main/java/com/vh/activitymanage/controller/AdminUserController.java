package com.vh.activitymanage.controller;

import com.vh.activitymanage.model.dto.UserDTO;
import com.vh.activitymanage.model.enums.UserStatus;
import com.vh.activitymanage.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PreAuthorize("hasAuthority('write')")
@RequestMapping("/admin/user")
public class AdminUserController {

    private static final Type USER_LIST_TYPE = (new TypeToken<List<UserDTO>>(){}).getType();

    private final AdminUserService adminUserService;
    private final ModelMapper mapper;

    @GetMapping("/all")
    public String getAllUsers(Model model){
        log.debug("GetAllUsers");

        var users = adminUserService.findAllActiveUsers();

        model.addAttribute("users", mapper.map(users, USER_LIST_TYPE));

        return "admin/users";
    }

    @PostMapping("/{id}/unban")
    public String updateUserStatusToActive(@PathVariable Long id){
        log.debug("UpdateUserTo ACTIVE");

        adminUserService.updateUserStatus(id, UserStatus.ACTIVE);

        return "redirect:/admin/user/all";
    }

    @PostMapping("/{id}/ban")
    public String updateUserStatusToBanned(@PathVariable Long id){
        log.debug("UpdateUserTo BANNED");

        adminUserService.updateUserStatus(id, UserStatus.BANNED);

        return "redirect:/admin/user/all";
    }
}