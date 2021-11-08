package com.giayshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giayshop.entity.Vote;

public interface VoteRepo extends JpaRepository<Vote, Long>{

}
