package com.vh.activitymanage.service;

import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.model.enums.ActivityStatus;

import java.util.List;
import java.util.Map;

public interface AdminActivityService {

    void deleteActivityById(Long id);

    void updateActivityStatusById(Long id, ActivityStatus status);

    Map<String, List<Activity>> findAllWaitingActivities();

    List<Activity> findAllActiveActivities();
}
