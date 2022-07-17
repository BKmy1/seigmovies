package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select r from tb_user u,tb_role r where u.role_id=r.role_auth and u.email=?1")
    List<Role> findAuthoritiesByAccount(String email);
}
