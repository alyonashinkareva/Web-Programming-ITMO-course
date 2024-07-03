package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.NoticeForm;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AddNoticePage extends Page {
    private final NoticeService noticeService;

    public AddNoticePage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/addnotice")
    public String register(Model model, HttpSession httpSession) {
        if (getUser(httpSession) == null) {
            setMessageError(httpSession, "only for logged in users");
            return "redirect:";
        }
        model.addAttribute("noticeForm", new NoticeForm());
        return "AddNoticePage";
    }

    @PostMapping("/addnotice")
    public String register(@Valid @ModelAttribute("noticeForm") NoticeForm noticeForm,
                           BindingResult bindingResult,
                           HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "AddNoticePage";
        }

        setNotice(httpSession, noticeService.createNotice(noticeForm));
        setMessage(httpSession, "You have created new notice with id = " + getNotice(httpSession).getId());

        return "redirect:";
    }
}
