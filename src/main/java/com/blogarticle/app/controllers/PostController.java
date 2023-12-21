package com.blogarticle.app.controllers;

import com.blogarticle.app.entities.Post;
import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.PaginatedPostResponse;
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
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable("postId") Integer postId)
    {
        return new ResponseEntity<>(this.postService.getPost(postId),HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<PaginatedPostResponse> getAllPost(@RequestParam(name = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                            @RequestParam(name = "pageSize",defaultValue = "10",required = false) Integer pageSize,
                                                            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
                                                            @RequestParam(name = "sortOrder",defaultValue = "1",required = false) Integer sortOrder)

    {
        return new ResponseEntity<>(this.postService.getAllPost(pageNumber,pageSize,sortBy,sortOrder),HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId)
    {
        return new ResponseEntity<>(this.postService.deletePost(postId),HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") Integer postId)
    {
        return new ResponseEntity<>(this.postService.updatePost(postDto,postId),HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<ApiResponse> searchByKeyword(@PathVariable("keyword") String keyword)
    {
        return  new ResponseEntity<>(this.postService.searchPostByTitleOrContentContaining(keyword),HttpStatus.OK);
    }

}
