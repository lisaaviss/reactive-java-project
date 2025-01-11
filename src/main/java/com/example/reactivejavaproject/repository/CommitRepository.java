package com.example.reactivejavaproject.repository;

import com.example.reactivejavaproject.entity.Commit;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface CommitRepository extends ReactiveCrudRepository<Commit, Long> {
    Flux<Commit> findByAuthorId(Long authorId);
    Flux<Commit> findByCommitTimestampBetween(LocalDateTime start, LocalDateTime end);
    Flux<Commit> findByStatus(String status);
    Flux<Commit> findByChangedFilesCountGreaterThanEqual(Integer count);
}
