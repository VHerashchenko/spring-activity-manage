package com.vh.activitymanage.service.impl;

import com.vh.activitymanage.model.entity.User;
import com.vh.activitymanage.model.enums.Role;
import com.vh.activitymanage.model.enums.UserStatus;
import com.vh.activitymanage.repository.AdminUserRepository;
import com.vh.activitymanage.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    AdminUserRepository adminUserRepository;

    @Autowired
    public AdminUserServiceImpl(AdminUserRepository adminUserRepository){
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    @Transactional
    public void updateUserStatus(Long id, UserStatus status) {
        adminUserRepository.changeUserStatusById(id, status);
    }

    @Override
    public List<User> findAllActiveUsers() {
        return adminUserRepository.findAllUserByRoleOrderByStatus(Role.USER);
    }
}
