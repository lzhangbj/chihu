package com.example.simpleforum.repository;

import com.example.simpleforum.model.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface QueryRepository extends JpaRepository<Query, Long> {
    Page<Query> findAllByOrderByCreateDateDesc(Pageable pageable);

    Optional<Query> findById(Query id);
}
