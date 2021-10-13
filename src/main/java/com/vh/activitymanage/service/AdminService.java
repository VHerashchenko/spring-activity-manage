package com.vh.activitymanage.service;

import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.model.entity.User;
import com.vh.activitymanage.model.enums.ActivityStatus;
import com.vh.activitymanage.model.enums.UserStatus;

import java.util.List;

public interface AdminService {

    void deleteActivityById(Long id);

    void updateActivityStatusById(Long id, ActivityStatus status);

    void updateUserStatus(Long id, UserStatus status);

    List<User> findAllActiveUsers();

    List<User> findAllBannedUsers();

    List<Activity> findAllWaitToActive();

    List<Activity> findAllWaitToDelete();

    List<Activity> findRelationWithCategoryById(Long id);
}
