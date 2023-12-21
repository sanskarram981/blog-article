package com.blogarticle.app.services;

import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.PaginatedPostResponse;
import com.blogarticle.app.payloads.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer catId);
    PostDto updatePost(PostDto postDto,Integer postId);
    ApiResponse deletePost(Integer postId);
    PostDto getPost(Integer postId);
    PaginatedPostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,Integer sortOrder);
    ApiResponse getAllPostByUser(Integer userId);
    ApiResponse getAllPostByCategory(Integer catId);
    ApiResponse searchPostByTitleOrContentContaining(String keyword);
}
