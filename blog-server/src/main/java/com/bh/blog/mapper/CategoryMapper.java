package com.bh.blog.mapper;

import com.bh.blog.model.Category;
import com.bh.blog.dto.request.CategoryRequest;
import com.bh.blog.dto.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryDtoToCategory(CategoryRequest category);
    List<CategoryResponse> categoriesToCategoryDto(List<Category> categories);
}
