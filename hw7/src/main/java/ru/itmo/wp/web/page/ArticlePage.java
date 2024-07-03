package ru.itmo.wp.web.page;

import org.checkerframework.checker.units.qual.A;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.annotation.Json;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    @Json
    private void makeArticle(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getSession().setAttribute("message", "This page is only for logged users");
            request.getSession().setAttribute("messageIsError", "error");
            throw new RedirectException("/index");
        }
        Article article = new Article();
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        article.setUserId(user.getId());
        article.setTitle(title);
        article.setText(text);
        articleService.validateArticle(title, text);
        articleService.save(article);
        request.getSession().setAttribute("message", "The article" + title + "was made!");
        throw new RedirectException("/index");
    }
}
