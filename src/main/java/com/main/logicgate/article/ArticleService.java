package com.main.logicgate.article;

import java.util.List;

public interface ArticleService {
    void createNewArticle(Article newRequestArticle, Long authorId);
    List<Article> getAllArticles();
    Article getArticle(Long articleId);
}
