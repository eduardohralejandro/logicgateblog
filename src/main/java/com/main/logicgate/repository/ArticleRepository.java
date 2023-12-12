package com.main.logicgate.repository;

import com.main.logicgate.model.ArticleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleModel, Long> {
}
