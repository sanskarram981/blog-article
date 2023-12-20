package com.blogarticle.app.services.impl;

import com.blogarticle.app.entities.Category;
import com.blogarticle.app.entities.Post;
import com.blogarticle.app.entities.User;
import com.blogarticle.app.exceptions.ResourceAlreadyFoundException;
import com.blogarticle.app.exceptions.ResourceNotFoundException;
import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.CategoryDto;
import com.blogarticle.app.payloads.PostDto;
import com.blogarticle.app.payloads.UserDto;
import com.blogarticle.app.repositories.CategoryRepository;
import com.blogarticle.app.repositories.PostRepository;
import com.blogarticle.app.repositories.UserRepository;
import com.blogarticle.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private PostRepository postRepo;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {
        Optional<Post> postOptional = this.postRepo.findByTitle(postDto.getTitle());
        if(postOptional.isPresent())
            throw new ResourceAlreadyFoundException("Post","title",postDto.getTitle());
        Optional<User> userOptional = this.userRepo.findById(userId);
        if(!userOptional.isPresent())
            throw new ResourceNotFoundException("User","id",Integer.toString(userId));
        Optional<Category> catOptional = this.categoryRepo.findById(catId);
        if(!catOptional.isPresent())
            throw new ResourceNotFoundException("Category","id",Integer.toString(catId));
        Post post = conversion(postDto);
        User user = userOptional.get();
        Category category = catOptional.get();
        post.setUser(user);
        post.setCategory(category);
        post = this.postRepo.save(post);
        return this.conversion(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer userId, Integer catId) {
        return null;
    }

    @Override
    public ApiResponse deletePost(Integer postId) {
        return null;
    }

    @Override
    public PostDto getPost(Integer postId) {
        return null;
    }

    @Override
    public ApiResponse getAllPost() {
        return null;
    }

    @Override
    public ApiResponse getAllPostByUser(Integer userId) {
        Optional<User> userOptional = this.userRepo.findById(userId);
        if(!userOptional.isPresent())
            throw new ResourceNotFoundException("User","id",Integer.toString(userId));
        User user = userOptional.get();
        List<Post> posts = this.postRepo.findAllByUser(user);
        List<PostDto> postDtos = posts.stream().map(this::conversion).collect(Collectors.toList());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("success");
        apiResponse.setSuccess(true);
        apiResponse.setData(postDtos);
        return apiResponse;
    }

    @Override
    public ApiResponse getAllPostByCategory(Integer catId) {
        Optional<Category> catOptional = this.categoryRepo.findById(catId);
        if(!catOptional.isPresent())
            throw new ResourceNotFoundException("Category","id",Integer.toString(catId));
        Category category = catOptional.get();
        List<Post> posts = this.postRepo.findAllByCategory(category);
        List<PostDto> postDtos = posts.stream().map(this::conversion).collect(Collectors.toList());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("success");
        apiResponse.setSuccess(true);
        apiResponse.setData(postDtos);
        return apiResponse;
    }

    @Override
    public ApiResponse searchPostByKeyword(String keyword) {
        return null;
    }

    private UserDto conversion(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
    private CategoryDto conversion(Category category)
    {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }
    private PostDto conversion(Post post)
    {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setImageUrl(post.getImageUrl());
        postDto.setAddedOn(post.getAddedOn());
        postDto.setUser(conversion(post.getUser()));
        postDto.setCategory(conversion(post.getCategory()));
        return postDto;
    }

    private Post conversion(PostDto postDto)
    {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());
        post.setAddedOn(new Date());
        return post;
    }

}
