package com.blogarticle.app.repositories;

import com.blogarticle.app.entities.Category;
import com.blogarticle.app.entities.Post;
import com.blogarticle.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findAllByUser(User user);
    List<Post> findAllByCategory(Category category);
    Optional<Post> findByTitle(String title);
    @Query("select p from Post p where p.title like :keyword or p.content like :keyword")
    List<Post> findAllByTitleOrContentContaining(@Param("keyword") String keyword);
}
