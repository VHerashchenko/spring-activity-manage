package com.vh.activitymanage.service;

import com.vh.activitymanage.model.entity.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long id);

    Category saveCategory(Category category);

    void deleteCategoryById(Long id);

    List<Category> findAll();

    List<Category> findAll(String nameColumn);
}
