package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.annotation.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/** @noinspection unused*/
public class IndexPage {
    private final ArticleService articleService = new ArticleService();
    private void action(HttpServletRequest request, Map<String, Object> view) {
        putMessage(request, view);
    }

    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        String flag = (String) request.getSession().getAttribute("messageIsError");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            if (Objects.equals(flag, "error")) {
                view.put("messageIsError", flag);
                request.getSession().removeAttribute("messageIsError");
            }
            request.getSession().removeAttribute("message");
        }
    }

    @Json
    public void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("articles", articleService.findAll());
    }
}
