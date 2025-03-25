package com.bh.blog.service;

import com.bh.blog.model.User;
import com.bh.blog.dto.ProfileDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean existsByEmail(String email);
    User getByEmail(String email);
    User save(User user);
    void updateProfile(ProfileDto profile);
    ProfileDto getProfileInfo(String email);
}
