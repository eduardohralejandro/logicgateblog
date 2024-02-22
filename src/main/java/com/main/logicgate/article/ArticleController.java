package com.main.logicgate.article;

import com.main.logicgate.common.enums.AuthorRole;
import com.main.logicgate.author.Author;
import com.main.logicgate.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/articles")
@RequiredArgsConstructor
@CrossOrigin
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    @GetMapping
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> addArticle(@RequestBody Article article) {
        Optional<Author> currentUser = authorRepository.findById(article.getAuthor().getUserId());
        AuthorRole authorRole = currentUser.get().getAuthorRole();

        if (authorRole == AuthorRole.REGULAR) {
            return new ResponseEntity<String>(
                    "Author role REGULAR has no enough author's permission to create an article, you must be an ADMIN", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            Article newArticle = new Article();

            newArticle.setTitle(article.getTitle());
            newArticle.setBody(article.getBody());
            newArticle.setTechTag(article.getTechTag());
            newArticle.setPhoto(article.getPhoto());
            newArticle.setProgrammingLanguage(article.getProgrammingLanguage());

            articleRepository.save(newArticle);
            return ResponseEntity.status(HttpStatus.CREATED).body("Article created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating article");
        }
    }

}
