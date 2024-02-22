package com.main.logicgate.author;

import com.main.logicgate.author.Author;
import com.main.logicgate.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@CrossOrigin
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getUsers() {
        return authorRepository.findAll();
    }
}
