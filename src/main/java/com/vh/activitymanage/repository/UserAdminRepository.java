package com.vh.activitymanage.repository;

import com.vh.activitymanage.model.entity.User;
import com.vh.activitymanage.model.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAdminRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(value = "update User u set u.status = :status where u.id = :id")
    void changeUserStatusById(@Param("id") Long id, @Param("status") UserStatus status);

    @Modifying
    @Query(value = "select u from User u where u.status = :status and u.role = 'USER'")
    List<User> findAllUsersWithStatus(@Param("status") UserStatus status);
}