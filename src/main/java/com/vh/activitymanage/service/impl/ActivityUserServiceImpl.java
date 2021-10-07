package com.vh.activitymanage.service.impl;

import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.repository.ActivityUserRepository;
import com.vh.activitymanage.service.ActivityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityUserServiceImpl implements ActivityUserService {
    ActivityUserRepository activityUserRepository;

    public void save(Activity activity){
        activityUserRepository.save(activity);
    }
}
