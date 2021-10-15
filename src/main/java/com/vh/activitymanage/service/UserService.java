package com.vh.activitymanage.service;

import com.vh.activitymanage.model.entity.User;

import java.util.List;

public interface UserService {
    void save(User note);

    List<User> findAllByUsernameNot();

    User findByUsername(String username);

    User findById(Long id);

    void deleteById(Long id);
}