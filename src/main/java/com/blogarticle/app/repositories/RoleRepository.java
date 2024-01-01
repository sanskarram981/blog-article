package com.blogarticle.app.repositories;

import com.blogarticle.app.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
