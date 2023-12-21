package com.blogarticle.app.repositories;

import com.blogarticle.app.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
