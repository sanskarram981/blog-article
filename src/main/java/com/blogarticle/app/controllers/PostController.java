package com.blogarticle.app.controllers;

import com.blogarticle.app.constants.SihaiConstants;
import com.blogarticle.app.payloads.ApiResponseDto;
import com.blogarticle.app.payloads.PaginatedPostResponse;
import com.blogarticle.app.payloads.PostDto;
import com.blogarticle.app.services.PostService;
import com.blogarticle.app.utils.AuditUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SihaiConstants.POST_URI)
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AuditUtils auditUtils;
    @PostMapping("/user/{userId}/category/{catId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable("userId") Integer userId,
                                              @PathVariable("catId") Integer catId)
    {
        auditUtils.audit("POSTS","POST",SihaiConstants.POST_URI+"/user"+Integer.toString(userId)+"/category"+Integer.toString(catId));
        return new ResponseEntity<>(this.postService.createPost(postDto,userId,catId), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponseDto> getAllPostByUser(@PathVariable("userId") Integer userId)
    {
        auditUtils.audit("POSTS","GET",SihaiConstants.POST_URI+"/user"+Integer.toString(userId));
        return new ResponseEntity<>(this.postService.getAllPostByUser(userId),HttpStatus.OK);
    }
    @GetMapping("/category/{catId}")
    public ResponseEntity<ApiResponseDto> getAllPostByCategory(@PathVariable("catId") Integer catId)
    {
        auditUtils.audit("POSTS","GET",SihaiConstants.POST_URI+"/category"+Integer.toString(catId));
        return new ResponseEntity<>(this.postService.getAllPostByCategory(catId),HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable("postId") Integer postId)
    {
        auditUtils.audit("POSTS","GET",SihaiConstants.POST_URI+Integer.toString(postId));
        return new ResponseEntity<>(this.postService.getPost(postId),HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<PaginatedPostResponse> getAllPost(@RequestParam(name = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                            @RequestParam(name = "pageSize",defaultValue = "10",required = false) Integer pageSize,
                                                            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
                                                            @RequestParam(name = "sortOrder",defaultValue = "1",required = false) Integer sortOrder)

    {
        auditUtils.audit("POSTS","GET",SihaiConstants.POST_URI+"/");
        return new ResponseEntity<>(this.postService.getAllPost(pageNumber,pageSize,sortBy,sortOrder),HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable("postId") Integer postId)
    {
        auditUtils.audit("POSTS","DELETE",SihaiConstants.POST_URI+"/"+Integer.toString(postId));
        return new ResponseEntity<>(this.postService.deletePost(postId),HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") Integer postId)
    {
        auditUtils.audit("POSTS","PUT",SihaiConstants.POST_URI+"/"+Integer.toString(postId));
        return new ResponseEntity<>(this.postService.updatePost(postDto,postId),HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<ApiResponseDto> searchByKeyword(@PathVariable("keyword") String keyword)
    {
        auditUtils.audit("POSTS","PUT",SihaiConstants.POST_URI+"/search"+keyword);
        return  new ResponseEntity<>(this.postService.searchPostByTitleOrContentContaining(keyword),HttpStatus.OK);
    }

}
