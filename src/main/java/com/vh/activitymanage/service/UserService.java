package com.vh.activitymanage.service;

import com.vh.activitymanage.model.entity.User;

public interface UserService {
    void save(User note);

    User findByUsername(String username);

    void deleteById(Integer id);
}