package com.vh.activitymanage.controller;

import com.vh.activitymanage.model.dto.CategoryDTO;
import com.vh.activitymanage.model.entity.Category;
import com.vh.activitymanage.service.CategoryService;
import com.vh.activitymanage.validator.CategoryRelationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/category")
@PreAuthorize("hasAuthority('write')")
public class CategoryController {
    private static final Type CATEGORY_LIST_TYPE = (new TypeToken<List<CategoryDTO>>(){}).getType();

    private final ModelMapper mapper;
    private final CategoryService categoryService;
    private final CategoryRelationValidator categoryRelationValidator;

    @GetMapping("/create")
    public String createCategory(Model model){
        log.debug("createCategory getMapping start");

        model.addAttribute("category", new CategoryDTO());

        return "category/categoryCreation";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") CategoryDTO categoryDTO){
        log.debug("updateCategory PostMapping");

        var category = mapper.map(categoryDTO, Category.class);

        categoryService.saveCategory(category);

        return "redirect:/category/all";
    }

    @GetMapping("/{id}/edit")
    public String edit(@ModelAttribute("category") CategoryDTO categoryDTO, @PathVariable Long id, Model model) {
        log.debug("editCategory Post");

        var category = categoryService.getCategoryById(id);

        model.addAttribute("category", mapper.map(category, CategoryDTO.class));

        return "category/categoryCreation";
    }

    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable Long id){
        log.debug("deleteCategory DeleteMapping");

        if(categoryRelationValidator.validate(id)){
            return "redirect:/category/all?error=true";
        }

        categoryService.deleteCategoryById(id);

        return "redirect:/category/all";
    }

    @GetMapping(value = {"/all", "/all?error=true"})
    public String getAllCategories(Model model, boolean error){
        log.debug("getAllCategories GetMapping");

        var categories = categoryService.findAll();
        model.addAttribute("categories", mapper.map(categories, CATEGORY_LIST_TYPE));

        if(error) {
            model.addAttribute("error", "error.delete.category");
        }
        return "category/allCategory";
    }
}