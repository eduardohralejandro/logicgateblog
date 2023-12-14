package com.main.logicgate.controller;

import com.main.logicgate.common.enums.ProgrammingLanguage;
import com.main.logicgate.common.enums.TechTag;
import com.main.logicgate.model.ArticleModel;
import com.main.logicgate.model.UserModel;
import com.main.logicgate.repository.ArticleRepository;

import com.main.logicgate.repository.UserRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/articles")
@CrossOrigin
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    public ArticleController(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<ArticleModel> getArticles() {
        return articleRepository.findAll();
    }

    record NewArticleRequest(
            String title,
            String body,
            String photo,
            ProgrammingLanguage programmingLanguage,
            TechTag techTag,
            UserModel author
    ) {}

    @PostMapping
    public ResponseEntity<String> addArticle(@RequestBody NewArticleRequest request) {
        try {
            ArticleModel newArticle = new ArticleModel();

            newArticle.setTitle(request.title);
            newArticle.setBody(request.body);
            newArticle.setTechTag(request.techTag);
            newArticle.setPhoto(request.photo);
            newArticle.setProgrammingLanguage(request.programmingLanguage);
            newArticle.setAuthor(request.author);


            articleRepository.save(newArticle);

            return ResponseEntity.status(HttpStatus.CREATED).body("Article created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating article");
        }
    }

}
