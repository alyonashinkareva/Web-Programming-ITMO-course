package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page{
    private final PostService postService;
    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public String getPost(Model model, @PathVariable Long id, HttpSession httpSession) {

        try {
            Post post = postService.findById(id);
            model.addAttribute("post", post);
            if (post == null) {
                throw new Exception();
            }
            model.addAttribute("post", post);
            httpSession.setAttribute("post", post);
            model.addAttribute("comment", new Comment());
            model.addAttribute("comments", postService.findById(id).getComments());
        } catch (Exception e) {
            putMessage(httpSession, "Page does not exist");
            return "redirect:";
        }
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String writeComment(@Valid @ModelAttribute("comment") Comment comment,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "PostPage";
        }

        postService.writeComment((Post) httpSession.getAttribute("post"), getUser(httpSession), comment);
        putMessage(httpSession, "Congratulations! You commented on a post.");
        return "redirect:/post/{id}";
    }
}
