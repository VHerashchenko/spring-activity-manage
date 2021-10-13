package com.vh.activitymanage.service;

import com.vh.activitymanage.model.entity.Activity;

import java.util.List;

public interface ActivityUserService {

    Activity getActivityById(Long id);

    Activity saveActivity(Activity activity);

    void deleteActivityRequestById(Long id);

    List<Activity> findAllWithCurrentUser();

    List<Activity> findAllWithCurrentUser(String nameColumn);
}