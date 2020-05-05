package com.example.simpleforum.service.impl;

import java.util.Optional;

import com.example.simpleforum.model.Comment;
import com.example.simpleforum.model.Post;
import com.example.simpleforum.repository.CommentRepository;
import com.example.simpleforum.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public Optional<Comment> findForId(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Page<Comment> findByPostOrderedByDatePageable(Post post, int page) {
        return commentRepository.findByPostOrderByCreateDateDesc(post, PageRequest.of(subtractPageByOne(page), 5));
    }

    @Override
    public Page<Comment> findAllOrderedByDatePageable(int page) {
        return commentRepository.findAllByOrderByCreateDateDesc(PageRequest.of(subtractPageByOne(page), 5));
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);

    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }

    
}
