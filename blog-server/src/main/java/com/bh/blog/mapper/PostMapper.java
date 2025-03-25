package com.bh.blog.mapper;

import com.bh.blog.dto.request.PostRequest;
import com.bh.blog.dto.response.CategoryResponse;
import com.bh.blog.model.Category;
import com.bh.blog.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "userEmail", target = "user.email")
    Post postDtoToPost(PostRequest postRequest);


}

