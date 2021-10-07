package com.vh.activitymanage.controller;

import com.vh.activitymanage.model.dto.CategoryDTO;
import com.vh.activitymanage.model.entity.Category;
import com.vh.activitymanage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/category")
public class CategoryController {
    private static final Type CATEGORY_LIST_TYPE = (new TypeToken<List<CategoryDTO>>(){}).getType();

    private final ModelMapper mapper;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable Long id, Model model){
        log.debug("getCategory by id");

        var category = categoryService.getCategoryById(id);

        model.addAttribute("category", mapper.map(category, CategoryDTO.class));

        return "category/categoryView";
    }

    @GetMapping("/create")
    public String createCategory(Model model){
        log.debug("createCategory getMapping start");

        model.addAttribute("category", new CategoryDTO());

        return "category/categoryCreation";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute("category") CategoryDTO categoryDTO, BindingResult bindingResult){
        log.debug("createCategory PostMapping");

        var category = mapper.map(categoryDTO, Category.class);

        categoryService.saveCategory(category);

        return "redirect:/category/" + category.getId();
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("category") CategoryDTO categoryDTO, Model model) {
        log.debug("editCategory Post");

        return createCategory(model);
    }

    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable Long id){
        log.debug("deleteCategory DeleteMapping");

        categoryService.deleteCategoryById(id);

        return "redirect:/category/all";
    }

    @GetMapping("/all")
    public String getAllCategories(Model model){
        log.debug("getAllCategories GetMapping");

        var categories = categoryService.findAll();

        model.addAttribute("categories", mapper.map(categories, CATEGORY_LIST_TYPE));

        return "category/allCategory";
    }
}