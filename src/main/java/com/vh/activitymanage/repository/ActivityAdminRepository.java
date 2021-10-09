package com.vh.activitymanage.repository;

import com.vh.activitymanage.model.entity.Activity;
import com.vh.activitymanage.model.enums.ActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityAdminRepository extends JpaRepository<Activity, Long> {
    @Modifying
    @Query(value = "update Activity vha set vha.status = :status where vha.id = :id")
    void changeActivityStatusById(@Param("id") Long id, @Param("status") ActivityStatus status);

    @Query(value = "select a from Activity a where a.status = :status")
    List<Activity> findAllActivityByStatus(@Param("status") ActivityStatus status);
}
