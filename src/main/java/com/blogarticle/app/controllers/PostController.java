package com.blogarticle.app.controllers;

import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.PostDto;
import com.blogarticle.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @PostMapping("/user/{userId}/category/{catId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable("userId") Integer userId,
                                              @PathVariable("catId") Integer catId)
    {
        return new ResponseEntity<>(this.postService.createPost(postDto,userId,catId), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getAllPostByUser(@PathVariable("userId") Integer userId)
    {
        return new ResponseEntity<>(this.postService.getAllPostByUser(userId),HttpStatus.OK);
    }
    @GetMapping("/category/{catId}")
    public ResponseEntity<ApiResponse> getAllPostByCategory(@PathVariable("catId") Integer catId)
    {
        return new ResponseEntity<>(this.postService.getAllPostByCategory(catId),HttpStatus.OK);
    }
}
