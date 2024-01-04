package com.blogarticle.app.controllers;

import com.blogarticle.app.constants.SihaiConstants;
import com.blogarticle.app.payloads.ApiResponseDto;
import com.blogarticle.app.payloads.CommentDto;
import com.blogarticle.app.services.CommentService;
import com.blogarticle.app.utils.AuditUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SihaiConstants.COMMENT_URI)
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private AuditUtils auditUtils;

    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable("userId") Integer userId,
                                                    @PathVariable("postId") Integer postId)
    {
        auditUtils.audit("COMMENT","POST",SihaiConstants.COMMENT_URI+"/user"+Integer.toString(userId)+"/post"+Integer.toString(postId));
        return new ResponseEntity<>(this.commentService.createComment(commentDto,userId,postId), HttpStatus.CREATED);
    }

    @PostMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable("commentId") Integer commentId)
    {
        auditUtils.audit("COMMENT","POST",SihaiConstants.COMMENT_URI+"/"+Integer.toString(commentId));
        return new ResponseEntity<>(this.commentService.updateComment(commentId,commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable("commentId") Integer commentId)
    {
        auditUtils.audit("COMMENT","DELETE",SihaiConstants.COMMENT_URI+"/"+Integer.toString(commentId));
        return new ResponseEntity<>(this.commentService.deleteComment(commentId), HttpStatus.OK);
    }
}
