package com.vh.activitymanage.repository;

import com.vh.activitymanage.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityUserRepository extends JpaRepository<Activity, Long> {
}
