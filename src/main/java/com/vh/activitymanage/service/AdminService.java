package com.vh.activitymanage.service;

import com.vh.activitymanage.model.enums.ActivityStatus;
import com.vh.activitymanage.model.enums.UserStatus;

public interface AdminService {

    void deleteActivityById(Long id);

    void updateActivityStatusById(Long id, ActivityStatus status);

    void updateUserStatus(Long id, UserStatus status);

    void findAllActiveUsers();

    void findAllBannedUsers();

    void findAllWaitToActive();

    void findAllWaitToDelete();
}
