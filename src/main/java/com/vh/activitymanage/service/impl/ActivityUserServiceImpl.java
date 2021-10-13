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

    public Activity saveActivity(Activity activity){
        activity.setUser(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
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
        return activityUserRepository.findAllByUserUsername(getCurrentUsername());
    }

    @Override
    public List<Activity> findAllWithCurrentUser(String nameColumn) {
        var username = getCurrentUsername();
        if(nameColumn != null){
            if(!nameColumn.equals("category-name"))
                return activityUserRepository.findAllByUserUsername(username, Sort.by(Sort.DEFAULT_DIRECTION, nameColumn));
            return activityUserRepository.findAllByUserUsernameOrderByCategoryNameAsc(username);
        }
        return findAllWithCurrentUser();
    }

    private String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}