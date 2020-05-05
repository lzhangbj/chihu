package com.example.simpleforum.repository;

import com.example.simpleforum.model.Post;

import java.util.Optional;

import com.example.simpleforum.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostOrderByCreateDateDesc(Post post, Pageable pageable);

    Page<Comment> findAllByOrderByCreateDateDesc(Pageable pageable);

    Optional<Comment> findById(Long id);
}
