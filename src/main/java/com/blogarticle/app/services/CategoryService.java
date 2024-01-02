package com.blogarticle.app.services;

import com.blogarticle.app.payloads.ApiResponseDto;
import com.blogarticle.app.payloads.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer catId);
    ApiResponseDto deleteCategory(Integer catId);
    CategoryDto getCategory(Integer catId);
    ApiResponseDto getAllCategory();
}
