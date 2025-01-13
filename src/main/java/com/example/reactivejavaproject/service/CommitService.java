package com.example.reactivejavaproject.service;

import com.example.reactivejavaproject.entity.Author;
import com.example.reactivejavaproject.entity.Commit;
import com.example.reactivejavaproject.repository.AuthorRepository;
import com.example.reactivejavaproject.repository.CommitRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CommitService {
    private final CommitRepository commitRepository;
    private final AuthorRepository authorRepository;
    private final Faker faker = new Faker();

    public CommitService(CommitRepository commitRepository, AuthorRepository authorRepository) {
        this.commitRepository = commitRepository;
        this.authorRepository = authorRepository;
    }

    public Mono<Void> generateData(int commitCount) {
        int authorCount = Math.max(1, commitCount / 10);
        List<Author> authors = new ArrayList<>();
        List<Commit> commits = new ArrayList<>();
        Random random = new Random();

        // Удаляем все записи
        return commitRepository.deleteAll()
                .then(authorRepository.deleteAll())
                .then(Mono.defer(() -> {
                    // Генерация авторов
                    for (int i = 0; i < authorCount; i++) {
                        authors.add(new Author(null, faker.name().fullName(), faker.internet().emailAddress()));
                    }

                    return authorRepository.saveAll(authors).collectList();
                }))
                .flatMap(savedAuthors -> {
                    // Генерация коммитов
                    for (int i = 0; i < commitCount; i++) {
                        Author randomAuthor = savedAuthors.get(random.nextInt(savedAuthors.size()));
                        commits.add(new Commit(
                                null, // ID коммита
                                randomAuthor.getId(), // Убедитесь, что это заполненное поле
                                LocalDateTime.now().minusDays(random.nextInt(30)),
                                faker.options().option("SUCCESS", "FAILURE", "PENDING"),
                                random.nextInt(20) + 1,
                                faker.lorem().sentence()
                        ));
                    }

                    return commitRepository.saveAll(commits).then();
                });
    }

    public Flux<Commit> getFilteredCommitsWithAuthorName(String authorName, String status, Integer filesChanged, LocalDateTime intervalFrom, LocalDateTime intervalTo, Integer limit) {
        // Фильтруем авторов по имени, если задано
        Mono<List<Author>> authorsMono = (authorName != null)
                ? authorRepository.findByNameContaining(authorName).collectList()
                : authorRepository.findAll().collectList();

        return authorsMono.flatMapMany(authors -> {
            List<Long> authorIds = authors.stream().map(Author::getId).toList();

            return commitRepository.findAll()
                    .filter(commit -> authorName == null || authorIds.contains(commit.getAuthorId()))
                    .filter(commit -> status == null || commit.getStatus().equalsIgnoreCase(status))
                    .filter(commit -> filesChanged == null || commit.getChangedFilesCount() >= filesChanged)
                    .filter(commit -> intervalFrom == null || !commit.getCommitTimestamp().isBefore(intervalFrom))
                    .filter(commit -> intervalTo == null || !commit.getCommitTimestamp().isAfter(intervalTo))
                    .sort((c1, c2) -> c2.getCommitTimestamp().compareTo(c1.getCommitTimestamp()))
                    .take(limit != null ? limit : Integer.MAX_VALUE)
                    .flatMap(commit -> {
                        // Дополнительно загружаем данные автора
                        return authorRepository.findById(commit.getAuthorId())
                                .map(author -> {
                                    commit.setAuthorName(author.getName());
                                    return commit;
                                });
                    });
        });
    }

}
