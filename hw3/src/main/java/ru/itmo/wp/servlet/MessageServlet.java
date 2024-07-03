package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MessageServlet extends HttpServlet {
    public static class messagePair {
        private final String user;
        private final String text;

        public messagePair(String user, String text) {
            this.user = user;
            this.text = text;
        }
    }
    ArrayList<messagePair> pairUserText = new ArrayList<messagePair>();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        response.setContentType("application/json");
        String json;
        PrintWriter writer = response.getWriter();
        switch (uri) {
             case "/message/auth":
                 String user = request.getParameter("user");
                 if (user == null || user.trim().isEmpty()) {
                     user = "";
                 }
                 session.setAttribute("user", user);
                 json = new Gson().toJson(user);
                 writer.print(json);
                 writer.flush();
                 break;
            case "/message/add":
                String text = request.getParameter("text");
                if (text != null) {
                    pairUserText.add(new messagePair((String) session.getAttribute("user"), text));
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
             case "/message/findAll":
                 json = new Gson().toJson(pairUserText);
                 writer.print(json);
                 writer.flush();
                 break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
         }
    }
}
