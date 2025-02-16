package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;
import ru.itmo.wp.model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static ru.itmo.wp.model.domain.Event.EventType.ENTER;

@SuppressWarnings({"unused"})
public class EnterPage {
    private final UserService userService = new UserService();
    private final EventService eventService = new EventService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String loginOrEmail = request.getParameter("loginOrEmail");
        String password = request.getParameter("password");

        userService.validateEnter(loginOrEmail, password);
        User user;
        if (userService.isEmail(loginOrEmail)) {
            user = userService.findByEmailAndPassword(loginOrEmail, password);
        } else {
            user = userService.findByLoginAndPassword(loginOrEmail, password);
        }

        Event event = new Event();
        event.setType(ENTER);
        event.setUserId(user.getId());
        eventService.makeEvent(event, user);
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("message", "Hello, " + user.getLogin());

        throw new RedirectException("/index");
    }
}
