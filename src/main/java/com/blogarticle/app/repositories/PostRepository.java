package com.blogarticle.app.repositories;

import com.blogarticle.app.entities.Category;
import com.blogarticle.app.entities.Post;
import com.blogarticle.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findAllByUser(User user);
    List<Post> findAllByCategory(Category category);
    Optional<Post> findByTitle(String title);
}
