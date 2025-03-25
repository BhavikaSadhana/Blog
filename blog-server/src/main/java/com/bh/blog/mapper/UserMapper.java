package com.bh.blog.mapper;

import com.bh.blog.model.User;
import com.bh.blog.dto.ProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    ProfileDto userToProfileDto(User user);
}
