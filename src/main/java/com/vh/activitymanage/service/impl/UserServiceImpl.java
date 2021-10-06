package com.vh.activitymanage.service.impl;

import com.vh.activitymanage.model.entity.User;
import com.vh.activitymanage.model.enums.Role;
import com.vh.activitymanage.model.enums.UserStatus;
import com.vh.activitymanage.repository.UserRepository;
import com.vh.activitymanage.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}