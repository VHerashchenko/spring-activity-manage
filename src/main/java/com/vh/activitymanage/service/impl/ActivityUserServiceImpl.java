package com.vh.activitymanage.service.impl;

import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.model.enums.ActivityStatus;
import com.vh.activitymanage.repository.ActivityUserRepository;
import com.vh.activitymanage.service.ActivityUserService;
import com.vh.activitymanage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityUserServiceImpl implements ActivityUserService {
    private final ActivityUserRepository activityUserRepository;
    private final UserService userService;

    @Override
    public Activity getActivityById(Long id) {
        return activityUserRepository.getById(id);
    }

    @Override
    @Transactional
    public Activity saveActivity(Activity activity){
        activity.setCreator(SecurityContextHolder.getContext().getAuthentication().getName());

        var users = activity.getUsers();
        users.add(userService.findByUsername(activity.getCreator()));
        activity.setUsers(users);

        activity.setUserCounter(users.size());
        activity.setStatus(ActivityStatus.WAIT_ACTIVE);

        return activityUserRepository.save(activity);
    }

    @Override
    @Transactional
    public void deleteActivityRequestById(Long id) {
        activityUserRepository.changeActivityStatusById(id, ActivityStatus.WAIT_DELETE);
    }

    @Override
    public List<Activity> findAllWithCurrentUser() {
        return activityUserRepository.findAllByCreator(getCurrentUsername());
    }

    @Override
    public List<Activity> findAllWithCurrentUser(String nameColumn) {
        var username = getCurrentUsername();
        if(nameColumn != null) {
            return activityUserRepository.findAllByCreator(username, Sort.by(Sort.DEFAULT_DIRECTION, nameColumn));
        }
        return findAllWithCurrentUser();
    }

    private String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}