package com.blogarticle.app.services.impl;

import com.blogarticle.app.entities.Category;
import com.blogarticle.app.exceptions.ResourceAlreadyFoundException;
import com.blogarticle.app.exceptions.ResourceNotFoundException;
import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.CategoryDto;
import com.blogarticle.app.repositories.CategoryRepository;
import com.blogarticle.app.services.CategoryService;
import com.blogarticle.app.utils.ValidateRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        ValidateRequestData.validate(categoryDto);
        Optional<Category> catOptional = this.categoryRepo.findByTitle(categoryDto.getTitle().toUpperCase());
        if(catOptional.isPresent())
            throw new ResourceAlreadyFoundException("Category","title",categoryDto.getTitle().toUpperCase());
        Category cat = this.conversion(categoryDto);
        cat = this.categoryRepo.save(cat);
        return this.conversion(cat);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
        ValidateRequestData.validate(categoryDto);
        Optional<Category> catOptional = this.categoryRepo.findById(catId);
        if(!catOptional.isPresent())
            throw new ResourceNotFoundException("Category","id",Integer.toString(catId));
        Category cat = catOptional.get();
        cat.setTitle(categoryDto.getTitle().toUpperCase());
        cat.setDescription(categoryDto.getDescription());
        cat = this.categoryRepo.save(cat);
        return this.conversion(cat);
    }

    @Override
    public ApiResponse deleteCategory(Integer catId) {
        return null;
    }

    @Override
    public CategoryDto getCategory(Integer catId) {
        return null;
    }

    @Override
    public ApiResponse getAllCategory() {
        return null;
    }

    private Category conversion(CategoryDto categoryDto)
    {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle().toUpperCase());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
    private CategoryDto conversion(Category category)
    {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

}
