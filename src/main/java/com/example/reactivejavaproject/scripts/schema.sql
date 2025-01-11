CREATE TABLE authors (
                         id SERIAL PRIMARY KEY, -- Уникальный идентификатор автора
                         name VARCHAR(255) NOT NULL, -- Имя автора
                         email VARCHAR(255) UNIQUE NOT NULL -- Email автора
);
CREATE TABLE commits (
                         id SERIAL PRIMARY KEY, -- Уникальный идентификатор коммита
                         commit_message VARCHAR(255) NOT NULL,
                         author_id INT NOT NULL, -- Внешний ключ на таблицу authors
                         commit_timestamp TIMESTAMP NOT NULL, -- Временной интервал создания коммита
                         status VARCHAR(50) NOT NULL, -- Статус коммита (например, SUCCESS, FAILURE)
                         changed_files_count INT NOT NULL, -- Количество измененных файлов
                         FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE
);