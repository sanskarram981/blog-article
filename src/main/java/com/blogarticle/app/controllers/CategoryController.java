package com.blogarticle.app.controllers;

import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.CategoryDto;
import com.blogarticle.app.services.CategoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
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
        return new ResponseEntity<>(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PostMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("catId") Integer catId)
    {
        return new ResponseEntity<>(this.categoryService.updateCategory(categoryDto,catId),HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer catId)
    {
        return new ResponseEntity<>(this.categoryService.deleteCategory(catId),HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer catId)
    {
        return new ResponseEntity<>(this.categoryService.getCategory(catId),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllCategory()
    {
        return new ResponseEntity<>(this.categoryService.getAllCategory(),HttpStatus.OK);
    }

}
