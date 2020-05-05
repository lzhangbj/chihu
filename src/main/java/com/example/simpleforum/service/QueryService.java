package com.example.simpleforum.service;

import com.example.simpleforum.model.Query;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface QueryService {

    Optional<Query> findForId(Long id);

    Query save(Query query);

    /**
     * Finds a {@link Page) of all {@link Post} ordered by date
     */
    Page<Query> findAllOrderedByDatePageable(int page);

    void delete(Query query);
}
