package com.bh.blog.service;

import com.bh.blog.model.Category;
import com.bh.blog.dto.request.CategoryRequest;
import com.bh.blog.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    Category save(CategoryRequest category);
    List<CategoryResponse> getAll();
}
