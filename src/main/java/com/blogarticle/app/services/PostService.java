package com.blogarticle.app.services;

import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer catId);
    PostDto updatePost(PostDto postDto,Integer userId,Integer catId);
    ApiResponse deletePost(Integer postId);
    PostDto getPost(Integer postId);
    ApiResponse getAllPost();
    ApiResponse getAllPostByUser(Integer userId);
    ApiResponse getAllPostByCategory(Integer catId);
    ApiResponse searchPostByKeyword(String keyword);
}
