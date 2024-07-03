package ru.itmo.wp.model.service;

import org.checkerframework.checker.units.qual.A;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
    public void validateArticle(String title, String text) throws ValidationException{
        if (title.length() == 0) {
            throw new ValidationException("Title is required");
        }

        if (text.length() == 0) {
            throw new ValidationException("Text is required");
        }

        if (title.length() > 128) {
            throw new ValidationException("Title can't be longer than 128 characters");
        }
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
