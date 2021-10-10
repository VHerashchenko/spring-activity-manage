package com.vh.activitymanage.service.impl;

import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.model.entity.User;
import com.vh.activitymanage.model.enums.ActivityStatus;
import com.vh.activitymanage.model.enums.Role;
import com.vh.activitymanage.model.enums.UserStatus;
import com.vh.activitymanage.repository.ActivityAdminRepository;
import com.vh.activitymanage.repository.UserAdminRepository;
import com.vh.activitymanage.repository.UserRepository;
import com.vh.activitymanage.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    ActivityAdminRepository activityAdminRepository;
    UserAdminRepository userAdminRepository;

    @Autowired
    public AdminServiceImpl(UserAdminRepository userAdminRepository, ActivityAdminRepository activityAdminRepository) {
        this.userAdminRepository = userAdminRepository;
        this.activityAdminRepository = activityAdminRepository;
    }

    @Override
    public void deleteActivityById(Long id) {
        activityAdminRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateActivityStatusById(Long id, ActivityStatus status) {
        activityAdminRepository.changeActivityStatusById(id, status);
    }

    @Override
    @Transactional
    public void updateUserStatus(Long id, UserStatus status) {
        userAdminRepository.changeUserStatusById(id, status);
    }

    @Override
    public List<User> findAllActiveUsers() {
        return userAdminRepository.findAllUsersWithStatus(UserStatus.ACTIVE, Role.USER);
    }

    @Override
    public List<User> findAllBannedUsers() {
        return userAdminRepository.findAllUsersWithStatus(UserStatus.BANNED, Role.USER);
    }

    @Override
    public List<Activity> findAllWaitToActive() {
        return activityAdminRepository.findAllActivityByStatus(ActivityStatus.WAIT_ACTIVE);
    }

    @Override
    public List<Activity> findAllWaitToDelete() {
        return activityAdminRepository.findAllActivityByStatus(ActivityStatus.WAIT_DELETE);
    }
}
