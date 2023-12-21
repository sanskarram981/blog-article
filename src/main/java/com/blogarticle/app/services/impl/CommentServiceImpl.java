package com.blogarticle.app.services.impl;

import com.blogarticle.app.entities.Comment;
import com.blogarticle.app.entities.Post;
import com.blogarticle.app.exceptions.ResourceNotFoundException;
import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.CommentDto;
import com.blogarticle.app.entities.User;
import com.blogarticle.app.payloads.UserDto;
import com.blogarticle.app.repositories.CommentRepository;
import com.blogarticle.app.repositories.PostRepository;
import com.blogarticle.app.repositories.UserRepository;
import com.blogarticle.app.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private CommentRepository commentRepo;
    @Override
    public CommentDto createComment(CommentDto commentDto,Integer userId,Integer postId) {
        Optional<User> userOptional = this.userRepo.findById(userId);
        if(!userOptional.isPresent())
            throw new ResourceNotFoundException("User","id",Integer.toString(userId));
        Optional<Post> postOptional = this.postRepo.findById(postId);
        if(!postOptional.isPresent())
            throw new ResourceNotFoundException("Post","id",Integer.toString(postId));
        User user = userOptional.get();
        Post post = postOptional.get();
        Comment comment = conversion(commentDto);
        comment.setUser(user);
        comment.setPost(post);
        comment = this.commentRepo.save(comment);
        return this.conversion(comment);
    }

    @Override
    public ApiResponse deleteComment(Integer commentId) {
        Optional<Comment> commentOptional = this.commentRepo.findById(commentId);
        if(!commentOptional.isPresent())
            throw new ResourceNotFoundException("Comment","id",Integer.toString(commentId));
        Comment comment = commentOptional.get();
        this.commentRepo.delete(comment);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Comment delete successfully");
        apiResponse.setSuccess(true);
        apiResponse.setData(null);
        return apiResponse;
    }

    @Override
    public CommentDto updateComment(Integer commentId, CommentDto commentDto) {
        Optional<Comment> commentOptional = this.commentRepo.findById(commentId);
        if(!commentOptional.isPresent())
            throw new ResourceNotFoundException("Comment","id",Integer.toString(commentId));
        Comment comment = commentOptional.get();
        comment.setContent(commentDto.getContent());
        comment = this.commentRepo.save(comment);
        return this.conversion(comment);
    }

    private Comment conversion(CommentDto commentDto)
    {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setAddedOn(new Date());
        return comment;
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

}
