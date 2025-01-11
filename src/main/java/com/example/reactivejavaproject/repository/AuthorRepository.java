package com.example.reactivejavaproject.repository;

import com.example.reactivejavaproject.entity.Author;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AuthorRepository extends ReactiveCrudRepository<Author, Long> {
    Mono<Author> findByName(String name);
}
