package com.main.logicgate.author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    void createAuthor(Author newAuthor, Long authorId);
}
