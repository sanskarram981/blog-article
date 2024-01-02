package com.blogarticle.app.controllers;

import com.blogarticle.app.payloads.ApiResponseDto;
import com.blogarticle.app.payloads.CommentDto;
import com.blogarticle.app.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable("userId") Integer userId,
                                                    @PathVariable("postId") Integer postId)
    {
        return new ResponseEntity<>(this.commentService.createComment(commentDto,userId,postId), HttpStatus.CREATED);
    }

    @PostMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable("commentId") Integer commentId)
    {
        return new ResponseEntity<>(this.commentService.updateComment(commentId,commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable("commentId") Integer commentId)
    {
        return new ResponseEntity<>(this.commentService.deleteComment(commentId), HttpStatus.OK);
    }
}
