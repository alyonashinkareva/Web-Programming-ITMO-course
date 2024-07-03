package ru.itmo.wp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itmo.wp.domain.Notice;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.NoticeService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class Page {
    private static final String USER_ID_SESSION_KEY = "userId";
    private static final String MESSAGE_SESSION_KEY = "message";

    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

    @ModelAttribute("user")
    public User getUser(HttpSession httpSession) {
        return userService.findById((Long) httpSession.getAttribute(USER_ID_SESSION_KEY));
    }

    @ModelAttribute("notice")
    public Notice getNotice(HttpSession httpSession) {
        return noticeService.findById((Long) httpSession.getAttribute("noticeId"));
    }

    @ModelAttribute("notices")
    public List<Notice> getNotices() {
        return noticeService.findAll();
    }

    @ModelAttribute("message")
    public String getMessage(HttpSession httpSession) {
        String message = (String) httpSession.getAttribute(MESSAGE_SESSION_KEY);
        httpSession.removeAttribute(MESSAGE_SESSION_KEY);
        return message;
    }

    @ModelAttribute("message_error")
    public String getMessageError(HttpSession httpSession) {
        String message_error = (String) httpSession.getAttribute("message_error");
        httpSession.removeAttribute("message_error");
        return message_error;
    }

    protected void setUser(HttpSession httpSession, User user) {
        if (user != null) {
            httpSession.setAttribute(USER_ID_SESSION_KEY, user.getId());
        } else {
            unsetUser(httpSession);
        }
    }

    protected void setNotice(HttpSession httpSession, Notice notice) {
        if (notice != null) {
            httpSession.setAttribute("noticeId", notice.getId());
        }
    }

    protected void unsetUser(HttpSession httpSession) {
        httpSession.removeAttribute(USER_ID_SESSION_KEY);
    }

    protected void setMessage(HttpSession httpSession, String message) {
        httpSession.setAttribute(MESSAGE_SESSION_KEY, message);
    }

    protected void setMessageError(HttpSession httpSession, String message_error) {
        httpSession.setAttribute("message_error", message_error);
    }
}
