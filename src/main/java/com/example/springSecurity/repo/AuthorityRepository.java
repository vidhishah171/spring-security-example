package com.example.springSecurity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springSecurity.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

	List<Authority> findAllByUsername(String username);
}
