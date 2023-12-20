package com.blogarticle.app.payloads;

import com.blogarticle.app.entities.Category;
import com.blogarticle.app.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private int id;
    private String title;
    private String content;
    private String imageUrl;
    private Date addedOn;
    private UserDto user;
    private CategoryDto category;
}
