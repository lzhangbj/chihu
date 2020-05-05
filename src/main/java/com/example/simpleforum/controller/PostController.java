package com.example.simpleforum.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.example.simpleforum.model.Post;
import com.example.simpleforum.model.Query;
import com.example.simpleforum.service.PostService;
import com.example.simpleforum.service.QueryService;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController {

    private final QueryService queryService;
    private final PostService postService;

    @Autowired
    public PostController(QueryService queryService, PostService postService) {
        this.queryService = queryService;
        this.postService = postService;
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String createNewPost(@Valid Post post,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/postForm";

        } else {
            postService.save(post);
            return "redirect:/post/" + post.getId();

        }
    }

    @RequestMapping(value = "/editPost/{query_id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable Long query_id,
                                 Model model) {

        Optional<Query> optionalQuery = queryService.findForId(query_id);

        if (optionalQuery.isPresent()) {
            Post post = new Post();
            post.setQuery(optionalQuery.get());
            model.addAttribute("post", post);
            return "/postForm";
        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String getPostWithId(@PathVariable Long id,
                                Model model) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "/post";
        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.POST)
    public String deletePostWithId(@PathVariable Long id) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            Long queryId = post.getQuery().getId();
            postService.delete(post);
            return "redirect:/query/" + queryId;
        } else {
            return "/error";
        }
    }
}
