package com.main.logicgate.author;

import com.main.logicgate.author.Author;
import com.main.logicgate.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return this.authorService.getAllAuthors();
    }

    @PostMapping("/create/{authorId}")
    public void createNewAuthor(@RequestBody Author newAuthor, @PathVariable Long authorId) {
        this.authorService.createAuthor(newAuthor, authorId);
    }
}
