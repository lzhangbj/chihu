package com.example.simpleforum.service.impl;

import java.util.Optional;

import com.example.simpleforum.model.Query;
import com.example.simpleforum.repository.QueryRepository;
import com.example.simpleforum.service.QueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImp implements QueryService {

    private final QueryRepository queryRepository;

    @Autowired
    public QueryServiceImp(QueryRepository queryRepository){
        this.queryRepository = queryRepository;
    }

    @Override
    public Optional<Query> findForId(Long id) {
        return queryRepository.findById(id);
    }

    @Override
    public Query save(Query query) {
        return queryRepository.saveAndFlush(query);
    }

    @Override
    public Page<Query> findAllOrderedByDatePageable(int page) {
        return queryRepository.findAllByOrderByCreateDateDesc(PageRequest.of(page, 5));
    }

    @Override
    public void delete(Query query) {
        queryRepository.delete(query);
    }
    
}