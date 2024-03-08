package com.spring.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

}