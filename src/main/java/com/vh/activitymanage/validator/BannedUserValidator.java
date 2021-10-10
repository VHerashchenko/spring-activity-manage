package com.vh.activitymanage.validator;

import com.vh.activitymanage.model.enums.UserStatus;
import com.vh.activitymanage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BannedUserValidator {

    private final UserService userService;

    public boolean validate() {

        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.findByUsername(username);

        if(user.getStatus().equals(UserStatus.BANNED)){
            return true;
        }

        return false;
    }
}