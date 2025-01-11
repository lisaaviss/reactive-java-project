package com.example.reactivejavaproject.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("commits")
public class Commit {
    @Id
    private Long id;
    private Long authorId;
    private LocalDateTime commitTimestamp;
    private String status;
    private Integer changedFilesCount;
    private String commitMessage;

    public Commit(Long id, Long authorId, LocalDateTime commitTimestamp, String status, Integer changedFilesCount, String commitMessage) {
        this.id = id;
        this.authorId = authorId;
        this.commitTimestamp = commitTimestamp;
        this.status = status;
        this.changedFilesCount = changedFilesCount;
        this.commitMessage = commitMessage;
    }
    public Commit(){

    }
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getCommitTimestamp() {
        return commitTimestamp;
    }

    public void setCommitTimestamp(LocalDateTime commitTimestamp) {
        this.commitTimestamp = commitTimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getChangedFilesCount() {
        return changedFilesCount;
    }

    public void setChangedFilesCount(Integer changedFilesCount) {
        this.changedFilesCount = changedFilesCount;
    }
    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }
}
