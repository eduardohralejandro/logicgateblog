package com.main.logicgate.controller;

import com.main.logicgate.common.enums.UserRole;
import com.main.logicgate.dto.NewArticleRequestDTO;
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
@RequestMapping("api/v1/articles")
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

    @PostMapping
    public ResponseEntity<String> addArticle(@RequestBody NewArticleRequestDTO request) {
        Optional<UserModel> currentUser = userRepository.findById(request.getAuthor().getId());

        if (currentUser.isPresent()) {
            UserRole authorRole = currentUser.get().getUserRole();

            if(authorRole == UserRole.REGULAR) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("" +
                        "User role REGULAR has no enough user's permission to create" +
                        "an article, you must be an ADMIN");
            }
        }

        try {
            ArticleModel newArticle = new ArticleModel();

            newArticle.setTitle(request.getTitle());
            newArticle.setBody(request.getBody());
            newArticle.setTechTag(request.getTechTag());
            newArticle.setPhoto(request.getPhoto());
            newArticle.setProgrammingLanguage(request.getProgrammingLanguage());
            newArticle.setAuthor(request.getAuthor());
            articleRepository.save(newArticle);

            return ResponseEntity.status(HttpStatus.CREATED).body("Article created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating article");
        }
    }

}
