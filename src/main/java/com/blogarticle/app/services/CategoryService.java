package com.blogarticle.app.services;

import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.CategoryDto;
import com.blogarticle.app.payloads.UserDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer catId);
    ApiResponse deleteCategory(Integer catId);
    CategoryDto getCategory(Integer catId);
    ApiResponse getAllCategory();
}
