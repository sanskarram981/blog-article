package com.blogarticle.app.services;

import com.blogarticle.app.payloads.ApiResponse;
import com.blogarticle.app.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer userId,Integer postId);
    ApiResponse deleteComment(Integer commentId);
    CommentDto updateComment(Integer commentId,CommentDto commentDto);
}
