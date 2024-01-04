package com.blogarticle.app.controllers;

import com.blogarticle.app.constants.SihaiConstants;
import com.blogarticle.app.payloads.ApiResponseDto;
import com.blogarticle.app.payloads.CategoryDto;
import com.blogarticle.app.services.CategoryService;
import com.blogarticle.app.utils.AuditUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SihaiConstants.CATEGORY_URI)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuditUtils auditUtils;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
    {
        auditUtils.audit("CATEGORY","POST",SihaiConstants.CATEGORY_URI+"/");
        return new ResponseEntity<>(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PostMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("catId") Integer catId)
    {
        auditUtils.audit("CATEGORY","POST",SihaiConstants.CATEGORY_URI+"/"+Integer.toString(catId));
        return new ResponseEntity<>(this.categoryService.updateCategory(categoryDto,catId),HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponseDto> deleteCategory(@PathVariable("catId") Integer catId)
    {
        auditUtils.audit("CATEGORY","DELETE",SihaiConstants.CATEGORY_URI+"/"+Integer.toString(catId));
        return new ResponseEntity<>(this.categoryService.deleteCategory(catId),HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer catId)
    {
        auditUtils.audit("CATEGORY","GET",SihaiConstants.CATEGORY_URI+"/"+Integer.toString(catId));
        return new ResponseEntity<>(this.categoryService.getCategory(catId),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseDto> getAllCategory()
    {
        auditUtils.audit("CATEGORY","GET",SihaiConstants.CATEGORY_URI+"/");
        return new ResponseEntity<>(this.categoryService.getAllCategory(),HttpStatus.OK);
    }

}
