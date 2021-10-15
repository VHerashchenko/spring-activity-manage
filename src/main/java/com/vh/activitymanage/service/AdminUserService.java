package com.vh.activitymanage.service;

import com.vh.activitymanage.model.entity.User;
import com.vh.activitymanage.model.enums.UserStatus;

import java.util.List;

public interface AdminUserService {
    void updateUserStatus(Long id, UserStatus status);

    List<User> findAllActiveUsers();
}