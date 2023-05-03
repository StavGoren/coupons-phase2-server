package com.stav.server.controllers;

import com.stav.server.beans.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @PostMapping
    public void createCategory(@RequestBody Category category) {
        System.out.println(category);
    }

    @PutMapping
    public void updateCategory(@RequestBody Category category) {
        System.out.println(category);
    }

    @GetMapping("{categoryId}")
    public Category getCategory(@PathVariable("categoryId") int id) {
        Category category = new Category(100, "Health");
        return category;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        return categories;
    }


    @DeleteMapping("{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") int id)   {

    }

}
