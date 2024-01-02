package com.blogarticle.app.services.impl;

import com.blogarticle.app.entities.Category;
import com.blogarticle.app.entities.Comment;
import com.blogarticle.app.entities.Post;
import com.blogarticle.app.entities.User;
import com.blogarticle.app.exceptions.ResourceAlreadyFoundException;
import com.blogarticle.app.exceptions.ResourceNotFoundException;
import com.blogarticle.app.payloads.*;
import com.blogarticle.app.repositories.CategoryRepository;
import com.blogarticle.app.repositories.PostRepository;
import com.blogarticle.app.repositories.UserRepository;
import com.blogarticle.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            throw new ResourceAlreadyFoundException("POST::","title",postDto.getTitle());
        Optional<User> userOptional = this.userRepo.findById(userId);
        if(!userOptional.isPresent())
            throw new ResourceNotFoundException("USER::","id",Integer.toString(userId));
        Optional<Category> catOptional = this.categoryRepo.findById(catId);
        if(!catOptional.isPresent())
            throw new ResourceNotFoundException("CATEGORY::","id",Integer.toString(catId));
        Post post = conversion(postDto);
        User user = userOptional.get();
        Category category = catOptional.get();
        post.setUser(user);
        post.setCategory(category);
        post = this.postRepo.save(post);
        return this.conversion(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto,Integer postId) {
        Optional<Post> postOptional = this.postRepo.findById(postId);
        if(!postOptional.isPresent())
            throw new ResourceNotFoundException("CATEGORY::","id",Integer.toString(postId));
        Post post = postOptional.get();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());
        post = this.postRepo.save(post);
        return this.conversion(post);
    }

    @Override
    public ApiResponseDto deletePost(Integer postId) {
        Optional<Post> postOptional = this.postRepo.findById(postId);
        if(!postOptional.isPresent())
            throw new ResourceNotFoundException("POST::","id",Integer.toString(postId));
        Post post = postOptional.get();
        this.postRepo.delete(post);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Post deleted successfully..");
        apiResponseDto.setSuccess(true);
        apiResponseDto.setData(null);
        return apiResponseDto;
    }

    @Override
    public PostDto getPost(Integer postId) {
        Optional<Post> postOptional = this.postRepo.findById(postId);
        if(!postOptional.isPresent())
            throw new ResourceNotFoundException("POST","id",Integer.toString(postId));
        Post post = postOptional.get();
        return this.conversion(post);
    }

    @Override
    public PaginatedPostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,Integer sortOrder) {
        Sort sort = sortOrder == -1 ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map(this::conversion).collect(Collectors.toList());
        PaginatedPostResponse paginatedPostResponse = new PaginatedPostResponse();
        paginatedPostResponse.setPageNumber(pagePost.getNumber());
        paginatedPostResponse.setPageSize(pagePost.getSize());
        paginatedPostResponse.setTotalElements(pagePost.getTotalElements());
        paginatedPostResponse.setTotalPages(pagePost.getTotalPages());
        paginatedPostResponse.setLastPage(pagePost.isLast());
        paginatedPostResponse.setData(postDtos);
        paginatedPostResponse.setTotalElement(pagePost.getNumberOfElements());
        return paginatedPostResponse;
    }

    @Override
    public ApiResponseDto getAllPostByUser(Integer userId) {
        Optional<User> userOptional = this.userRepo.findById(userId);
        if(!userOptional.isPresent())
            throw new ResourceNotFoundException("User","id",Integer.toString(userId));
        User user = userOptional.get();
        List<Post> posts = this.postRepo.findAllByUser(user);
        List<PostDto> postDtos = posts.stream().map(this::conversion).collect(Collectors.toList());
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("success");
        apiResponseDto.setSuccess(true);
        apiResponseDto.setData(postDtos);
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto getAllPostByCategory(Integer catId) {
        Optional<Category> catOptional = this.categoryRepo.findById(catId);
        if(!catOptional.isPresent())
            throw new ResourceNotFoundException("Category","id",Integer.toString(catId));
        Category category = catOptional.get();
        List<Post> posts = this.postRepo.findAllByCategory(category);
        List<PostDto> postDtos = posts.stream().map(this::conversion).collect(Collectors.toList());
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("success");
        apiResponseDto.setSuccess(true);
        apiResponseDto.setData(postDtos);
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto searchPostByTitleOrContentContaining(String keyword) {
        List<Post> posts = this.postRepo.findAllByTitleOrContentContaining("%"+keyword+"%");
        List<PostDto> postDtos = posts.stream().map(this::conversion).collect(Collectors.toList());
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("success");
        apiResponseDto.setSuccess(true);
        apiResponseDto.setData(postDtos);
        return apiResponseDto;
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
        //
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setImageUrl(post.getImageUrl());
        postDto.setAddedOn(post.getAddedOn());
        postDto.setUser(conversion(post.getUser()));
        postDto.setCategory(conversion(post.getCategory()));
        List<CommentDto> comments = post.getComments().stream().map(this::conversion).collect(Collectors.toList());
        postDto.setComments(comments);
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

    private CommentDto conversion(Comment comment)
    {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setAddedOn(comment.getAddedOn());
        commentDto.setUser(conversion(comment.getUser()));
        return commentDto;
    }

}
