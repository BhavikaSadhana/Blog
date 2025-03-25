package com.bh.blog.service.impl;

import com.bh.blog.mapper.CategoryMapper;
import com.bh.blog.model.Category;
import com.bh.blog.dto.request.CategoryRequest;
import com.bh.blog.dto.response.CategoryResponse;
import com.bh.blog.repository.CategoryRepository;
import com.bh.blog.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(CategoryRequest category) {
        return categoryRepository.save(CategoryMapper.INSTANCE.categoryDtoToCategory(category));
    }

    @Override
    public List<CategoryResponse> getAll() {
        return CategoryMapper.INSTANCE.categoriesToCategoryDto(categoryRepository.findAll());
    }
}
