package com.blogarticle.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private Date addedOn;
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    @ManyToOne
    @JoinColumn(name = "pid")
    private Post post;
}
