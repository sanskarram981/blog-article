package com.blogarticle.app.controllers;

import com.blogarticle.app.payloads.CategoryDto;
import com.blogarticle.app.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
    {
        return new ResponseEntity<>(this.categoryService.createCategory(categoryDto), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("userId") Integer userId)
    {
        return new ResponseEntity<>(this.categoryService.updateCategory(categoryDto,userId),HttpStatus.OK);
    }

}
