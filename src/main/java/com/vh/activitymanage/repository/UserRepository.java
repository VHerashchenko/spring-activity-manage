package com.vh.activitymanage.repository;

import com.vh.activitymanage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAllByUsernameNot(String username);
}