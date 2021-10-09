package com.vh.activitymanage.service.impl;

import com.vh.activitymanage.model.enums.ActivityStatus;
import com.vh.activitymanage.model.enums.UserStatus;
import com.vh.activitymanage.repository.ActivityAdminRepository;
import com.vh.activitymanage.repository.UserAdminRepository;
import com.vh.activitymanage.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminServiceImpl implements AdminService {
    ActivityAdminRepository activityAdminRepository;
    UserAdminRepository userAdminRepository;

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
    public void findAllActiveUsers() {
        userAdminRepository.findAllUsersWithStatus(UserStatus.ACTIVE);
    }

    @Override
    public void findAllBannedUsers() {
        userAdminRepository.findAllUsersWithStatus(UserStatus.BANNED);
    }

    @Override
    public void findAllWaitToActive() {
        activityAdminRepository.findAllActivityByStatus(ActivityStatus.WAIT_ACTIVE);
    }

    @Override
    public void findAllWaitToDelete() {
        activityAdminRepository.findAllActivityByStatus(ActivityStatus.WAIT_DELETE);
    }
}
