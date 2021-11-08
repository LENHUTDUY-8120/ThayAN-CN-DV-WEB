package com.giayshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Long>{

}
