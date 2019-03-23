package com.openmatics.quiz.controller;

import com.openmatics.quiz.dao.UserDao;
import com.openmatics.quiz.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

    @GetMapping("/showAllUsers")
    public String showAllUsers(Model model) {
        StringBuilder stringBuilder = new StringBuilder();
        userDao.findAll().stream().map(UserEntity::toString).forEach(us -> stringBuilder.append(us + '\n'));
        model.addAttribute("users",stringBuilder.toString());
        return "usersList.html";
    }

}
