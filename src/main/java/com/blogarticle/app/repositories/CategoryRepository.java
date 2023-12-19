package com.blogarticle.app.repositories;

import com.blogarticle.app.entities.Category;
import com.blogarticle.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByTitle(String title);
}
