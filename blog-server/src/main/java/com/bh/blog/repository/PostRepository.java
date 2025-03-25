package com.bh.blog.repository;

import com.bh.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
     Long countPostByCategory_Name(String category_name);
}
