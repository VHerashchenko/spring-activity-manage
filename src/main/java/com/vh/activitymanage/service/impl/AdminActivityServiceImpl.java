package com.vh.activitymanage.service.impl;

import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.model.enums.ActivityStatus;
import com.vh.activitymanage.repository.ActivityAdminRepository;
import com.vh.activitymanage.repository.AdminUserRepository;
import com.vh.activitymanage.service.AdminActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminActivityServiceImpl implements AdminActivityService {
    ActivityAdminRepository activityAdminRepository;
    AdminUserRepository adminUserRepository;

    @Autowired
    public AdminActivityServiceImpl(AdminUserRepository adminUserRepository, ActivityAdminRepository activityAdminRepository) {
        this.adminUserRepository = adminUserRepository;
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
    public Map<String, List<Activity>> findAllWaitingActivities() {
        var allActivities = activityAdminRepository.findAllByStatusIsNot(ActivityStatus.ACTIVE);

        return Map.of(
                "waitActive", allActivities.stream()
                        .filter(x -> x.getStatus().equals(ActivityStatus.WAIT_ACTIVE))
                        .collect(Collectors.toList()),
                "waitDelete", allActivities.stream()
                        .filter(x -> x.getStatus().equals(ActivityStatus.WAIT_DELETE))
                        .collect(Collectors.toList()));

    }

    @Override
    public List<Activity> findAllActivities() {
        return activityAdminRepository.findAll();
    }

    @Override
    public List<Activity> findAllActivities(String sort) {
        if(sort != null){
            return activityAdminRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, sort));
        }
        return findAllActivities();
    }
}