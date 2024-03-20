package com.main.logicgate.article.implementation;

import com.main.logicgate.article.Article;
import com.main.logicgate.article.ArticleRepository;
import com.main.logicgate.article.ArticleService;
import com.main.logicgate.author.Author;
import com.main.logicgate.author.AuthorRepository;
import com.main.logicgate.common.enums.AuthorRole;
import com.main.logicgate.exception.ForbiddenException;
import com.main.logicgate.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleImpl implements ArticleService {
    AuthorRepository authorRepository;
    ArticleRepository articleRepository;

    public ArticleImpl(ArticleRepository articleRepository, AuthorRepository authorRepository) {
        this.articleRepository = articleRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void createNewArticle(Article newRequestArticle, Long authorId) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found with id: " + authorId));
        if (!author.getAuthorRole().equals(AuthorRole.ADMIN)) throw new ForbiddenException("Not enough credentials");
        newRequestArticle.setAuthor(author);
        articleRepository.save(newRequestArticle);
    }

    @Override
    public List<Article> getAllArticles() {
        return this.articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long articleId) {
        return this.articleRepository.findById(articleId).orElseThrow(() -> new NotFoundException("Article not found with id: " + articleId));
    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = this.articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("Article not found with id: " + articleId));
        this.articleRepository.deleteById(articleId);
    }
}
