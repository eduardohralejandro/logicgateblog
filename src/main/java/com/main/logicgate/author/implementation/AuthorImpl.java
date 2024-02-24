package com.main.logicgate.author.implementation;

import com.main.logicgate.author.Author;
import com.main.logicgate.author.AuthorRepository;
import com.main.logicgate.author.AuthorService;
import com.main.logicgate.common.enums.AuthorRole;
import com.main.logicgate.exception.BadRequestException;
import com.main.logicgate.exception.ForbiddenException;
import com.main.logicgate.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public void createAuthor(Author newAuthor, Long authorId) {
        Author currentAuthor = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found with id: " + authorId));

        if (!currentAuthor.getAuthorRole().equals(AuthorRole.ADMIN)) {
            throw new ForbiddenException("No authorized to CREATE users, contact ADMIN to elevate credentials");
        }

        try {
            this.authorRepository.save(newAuthor);
        } catch (BadRequestException exception) {
            throw new BadRequestException(exception + "Bad request on creating new Author: " + newAuthor.getName());
        }
    }
}
