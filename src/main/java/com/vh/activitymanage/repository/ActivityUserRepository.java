package com.vh.activitymanage.repository;

import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.model.enums.ActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityUserRepository extends JpaRepository<Activity, Long> {
    @Modifying
    @Query(value = "update Activity vha set vha.status = :status where vha.id = :id")
    void changeActivityStatusById(@Param("id") Long id, @Param("status") ActivityStatus status);
}