package com.bh.blog.service;

import com.bh.blog.dto.request.CommentRequest;
import com.bh.blog.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentByPostId(Long id);
    void save(CommentRequest comment);
}
