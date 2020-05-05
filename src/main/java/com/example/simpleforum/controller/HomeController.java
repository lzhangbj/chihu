package com.example.simpleforum.controller;

import com.example.simpleforum.model.Query;
import com.example.simpleforum.service.QueryService;
import com.example.simpleforum.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final QueryService queryService;

    @Autowired
    public HomeController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "0") int page,
                       Model model) {

        Page<Query> queries = queryService.findAllOrderedByDatePageable(page);
        Pager<Query> pager = new Pager<Query>(queries);

        model.addAttribute("pager", pager);

        return "/home";
    }
}
