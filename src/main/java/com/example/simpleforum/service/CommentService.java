package com.example.simpleforum.service;

import com.example.simpleforum.model.Post;
import com.example.simpleforum.model.Comment;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CommentService {

    Optional<Comment> findForId(Long id);

    Comment save(Comment comment);

    /**
     * Finds a {@link Page) of {@link Post} of provided user ordered by date
     */
    Page<Comment> findByPostOrderedByDatePageable(Post post, int page);

    /**
     * Finds a {@link Page) of all {@link Post} ordered by date
     */
    Page<Comment> findAllOrderedByDatePageable(int page);

    void delete(Comment comment);
}
