package com.bh.blog.service;

import com.bh.blog.dto.request.PostRequest;
import com.bh.blog.dto.response.PostDetailResponse;
import com.bh.blog.dto.response.PostListResponse;
import com.bh.blog.dto.response.PostNumberInCategory;
import com.bh.blog.model.Post;

import java.util.List;

public interface PostService {
    PostRequest save(PostRequest post);
    List<PostListResponse> getAll();
    PostDetailResponse getPostDetail(Long id);
    Post getById(Long postId);
    List<PostNumberInCategory> postCategoryCount();
}
