package com.vh.activitymanage.repository;

import com.vh.activitymanage.model.entity.User;
import com.vh.activitymanage.model.enums.Role;
import com.vh.activitymanage.model.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminUserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(value = "update User u set u.status = :status where u.id = :id")
    void changeUserStatusById(@Param("id") Long id, @Param("status") UserStatus status);

    List<User> findAllUserByRoleOrderByStatus(@Param("role") Role role);
}