package com.example.simpleforum.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.example.simpleforum.model.Query;
import com.example.simpleforum.service.QueryService;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QueryController {

    private final QueryService queryService;

    @Autowired
    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @RequestMapping(value = "/newQuery", method = RequestMethod.GET)
    public String newQuery(Model model) {
            Query query = new Query();
            model.addAttribute("query", query);
            return "/queryForm";
    }

    @RequestMapping(value = "/newQuery", method = RequestMethod.POST)
    public String createNewQuery(@Valid Query query,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.print("############ binding errors #############");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
            
                    System.out.println(fieldError.getCode());
                }
            
                if(object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;
            
                    System.out.println(objectError.getCode());
                }
            }
            System.out.print("############ binding errors end #############");
            return "/queryForm";
        } else {
            queryService.save(query);
            return "redirect:/query/" + query.getId();
        }
    }

    @RequestMapping(value = "/editQuery/{id}", method = RequestMethod.GET)
    public String editQueryWithId(@PathVariable Long id,
                                 Model model) {

        Optional<Query> optionalQuery = queryService.findForId(id);

        if (optionalQuery.isPresent()) {
            Query query = optionalQuery.get();
            model.addAttribute("query", query);
            return "/queryForm";
        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public String getQueryWithId(@PathVariable Long id,
                                Model model) {

        Optional<Query> optionalQuery = queryService.findForId(id);

        if (optionalQuery.isPresent()) {
            Query query = optionalQuery.get();
            model.addAttribute("query", query);
            return "/query";
        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.POST)
    public String deleteQueryWithId(@PathVariable Long id) {

        Optional<Query> optionalQuery = queryService.findForId(id);

        if (optionalQuery.isPresent()) {
            Query query = optionalQuery.get();
            queryService.delete(query);
            return "redirect:/home";
        } else {
            return "/error";
        }
    }
}
