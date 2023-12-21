package com.blogarticle.app.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private int id;
    private String content;
    private Date addedOn;
    private UserDto user;
}
