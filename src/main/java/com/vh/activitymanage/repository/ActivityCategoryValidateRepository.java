package com.vh.activitymanage.repository;

import com.vh.activitymanage.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityCategoryValidateRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByCategory_Id(Long id);
}
