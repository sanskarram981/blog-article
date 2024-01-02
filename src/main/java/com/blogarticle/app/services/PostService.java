package com.blogarticle.app.services;

import com.blogarticle.app.payloads.ApiResponseDto;
import com.blogarticle.app.payloads.PaginatedPostResponse;
import com.blogarticle.app.payloads.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer catId);
    PostDto updatePost(PostDto postDto,Integer postId);
    ApiResponseDto deletePost(Integer postId);
    PostDto getPost(Integer postId);
    PaginatedPostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,Integer sortOrder);
    ApiResponseDto getAllPostByUser(Integer userId);
    ApiResponseDto getAllPostByCategory(Integer catId);
    ApiResponseDto searchPostByTitleOrContentContaining(String keyword);
}
