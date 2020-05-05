package com.example.simpleforum.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.example.simpleforum.model.Comment;
import com.example.simpleforum.model.Post;
import com.example.simpleforum.service.CommentService;
import com.example.simpleforum.service.PostService;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommentController {

    private final PostService postService;
    private final CommentService commentService;


    @Autowired
    public CommentController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/newComment/{post_id}", method = RequestMethod.GET)
    public String newComment(@PathVariable Long post_id, 
                                Model model) {

        Optional<Post> post = postService.findForId(post_id);

        if (post.isPresent()) {
            Comment comment = new Comment();
            comment.setPost(post.get());

            model.addAttribute("comment", comment);

            return "/commentForm";

        }else{
            return "/error";
        }

    }

    @RequestMapping(value = "/newComment", method = RequestMethod.POST)
    public String createNewComment(@Valid Comment comment,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/commentForm";

        } else {
            commentService.save(comment);

            return "redirect:/post/" + comment.getPost().getId();

        }
    }
}
