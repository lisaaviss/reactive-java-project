INSERT INTO authors (name, email) VALUES ('Alice Johnson', 'alice.johnson@example.com');
INSERT INTO authors (name, email) VALUES ('Bob Smith', 'bob.smith@example.com');
INSERT INTO authors (name, email) VALUES ('Charlie Brown', 'charlie.brown@example.com');

INSERT INTO commits (commit_message, author_id, commit_timestamp, status, changed_files_count)
VALUES ('Initial commit', 1, '2025-01-01 10:00:00', 'SUCCESS', 5);

INSERT INTO commits (commit_message, author_id, commit_timestamp, status, changed_files_count)
VALUES ('Bug fix: Fixed issue #42', 2, '2025-01-02 14:30:00', 'FAILURE', 2);

INSERT INTO commits (commit_message, author_id, commit_timestamp, status, changed_files_count)
VALUES ('Added new feature: User authentication', 3, '2025-01-03 09:15:00', 'SUCCESS', 8);

INSERT INTO commits (commit_message, author_id, commit_timestamp, status, changed_files_count)
VALUES ('Code refactor for better performance', 1, '2025-01-04 18:45:00', 'SUCCESS', 12);

INSERT INTO commits (commit_message, author_id, commit_timestamp, status, changed_files_count)
VALUES ('Updated documentation', 2, '2025-01-05 12:00:00', 'PENDING', 3);
