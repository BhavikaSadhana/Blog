package com.bh.blog.service.impl;

import com.bh.blog.dto.CommentDto;
import com.bh.blog.dto.request.PostRequest;
import com.bh.blog.dto.response.PostDetailResponse;
import com.bh.blog.dto.response.PostListResponse;
import com.bh.blog.dto.response.PostNumberInCategory;
import com.bh.blog.mapper.PostMapper;
import com.bh.blog.model.Category;
import com.bh.blog.model.Comment;
import com.bh.blog.model.Post;
import com.bh.blog.model.User;
import com.bh.blog.repository.CategoryRepository;
import com.bh.blog.repository.CommentRepository;
import com.bh.blog.repository.PostRepository;
import com.bh.blog.repository.UserRepository;
import com.bh.blog.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImp implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImp.class);
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostRequest save(PostRequest post) {
        try {
            Post p = PostMapper.INSTANCE.postDtoToPost(post);
            User user = userRepository.findByEmail(post.getUserEmail()).get();
            p.setUser(user);
            p.setCreatedAt(LocalDateTime.now());
            postRepository.save(p);
        } catch (Exception e) {
            logger.error("Cannot save to database. {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return post;
    }

    @Override
    public List<PostListResponse> getAll() {
        List<PostListResponse> postListResponses = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            String author = "";
            if (post.getUser().getFirstName() != null) {
                author = post.getUser().getFirstName() + " " + post.getUser().getLastName();
            }
            String contentPreview = "...";
            int startIndex = post.getContent().indexOf("<p");
            int endIndex = post.getContent().indexOf("</p");
            if (startIndex != -1 && endIndex != -1) {
                contentPreview = post.getContent().substring(startIndex, endIndex);
            }
            postListResponses.add(new PostListResponse(
                    post.getId(),
                    post.getTitle(),
                    post.getCategory().getName(),
                    author,
                    contentPreview,
                    post.getCreatedAt()
            ));
        }
        return postListResponses;
    }

    @Override
    public PostDetailResponse getPostDetail(Long id) {
        Post post = postRepository.getOne(id);
        List<Comment> postComments = commentRepository.findByPost_Id(post.getId());
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : postComments) {
            commentDtos.add(new CommentDto(
                    comment.getComment(),
                    comment.getUser().getFirstName() + " " + comment.getUser().getLastName(),
                    comment.getCreatedAt()
            ));
        }
        PostDetailResponse postDetailResponse = new PostDetailResponse();
        String author = "";
        if (post.getUser().getFirstName() != null) {
            author = post.getUser().getFirstName() + " " + post.getUser().getLastName();
        }
        postDetailResponse.setId(post.getId());
        postDetailResponse.setAuthor(author);
        postDetailResponse.setCategory(post.getCategory().getName());
        postDetailResponse.setContent(post.getContent());
        postDetailResponse.setCreatedAt(post.getCreatedAt());
        postDetailResponse.setTitle(post.getTitle());
        postDetailResponse.setComments(commentDtos);
        return postDetailResponse;
    }

    @Override
    public Post getById(Long postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public List<PostNumberInCategory> postCategoryCount() {
        List<Category> categories = this.categoryRepository.findAll();
        List<PostNumberInCategory> postCategoryCount = new ArrayList<>();
        for (Category cat: categories) {
            Long postCount = this.postRepository.countPostByCategory_Name(cat.getName());
            if (postCount > 0) {
                postCategoryCount.add(new PostNumberInCategory(cat.getName(), postCount));
            }
        }
        return postCategoryCount;
    }
}
