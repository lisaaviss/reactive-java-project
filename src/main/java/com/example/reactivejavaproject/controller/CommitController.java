package com.example.reactivejavaproject.controller;

import com.example.reactivejavaproject.entity.Commit;
import com.example.reactivejavaproject.service.CommitService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/commits")
public class CommitController {
    private final CommitService commitService;

    public CommitController(CommitService commitService) {
        this.commitService = commitService;
    }

    @GetMapping
    public Flux<Commit> getFilteredCommits(
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer minChangedFiles,
            @RequestParam(required = false) LocalDateTime startTime,
            @RequestParam(required = false) LocalDateTime endTime,
            @RequestParam(required = false) Integer limit
    ) {
        return commitService.getFilteredCommits(authorName, status, minChangedFiles, startTime, endTime, limit);
    }

    @PostMapping("/generate")
    public Mono<Void> generateCommits(@RequestParam int commitCount) {
        return commitService.generateData(commitCount);
    }
}
