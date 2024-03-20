package com.main.logicgate.article;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/all")
    public List<Article> getAllArticles() {
        return this.articleService.getAllArticles();
    }

    @GetMapping("/{articleId}")
    public Article getArticle(@PathVariable Long articleId) {
        return this.articleService.getArticle(articleId);
    }

    @CrossOrigin
    @PostMapping("/create/{authorId}")
    public void createNewArticle(@RequestBody Article article, @PathVariable Long authorId) {
        this.articleService.createNewArticle(article, authorId);
    }

    @CrossOrigin
    @DeleteMapping("/delete/{articleId}")
    public void deleteArticle(@PathVariable Long articleId) {
        this.articleService.deleteArticle(articleId);
    }
}
