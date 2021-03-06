package com.openmatics.quiz.controller;

import com.openmatics.quiz.dao.UserDao;
import com.openmatics.quiz.model.UserEntity;
import com.openmatics.quiz.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Random;

@Controller
public class SecretController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private WalletService walletService;

    @GetMapping("/secret")
    public String index(@ModelAttribute UserEntity userEntity) {
        return "form.html";
    }

    @PostMapping("/process-form")
    public String processForm(@ModelAttribute UserEntity userEntity, HttpSession httpSession) {
        userEntity.setEmail(userEntity.getEmail().toLowerCase());

        UserEntity existedEntity = null;
        if (httpSession.getAttribute("userEntity")!=null) {
            existedEntity = (UserEntity)(httpSession.getAttribute("userEntity"));
        } else {
            existedEntity = userDao.find(userEntity.getEmail());
        }

        // session is good for detect multi-mail users
        userEntity.setSessionId(httpSession.getId());

        if (existedEntity != null) {
            // do not regenerate wallet for email
            userEntity.setWallet(existedEntity.getWallet());
            userEntity.setEmail(existedEntity.getEmail());
            userDao.merge(userEntity);
        } else {
            // wallet is based on hash of email
            String wallet = walletService.hashWallet(LocalDateTime.now().getHour(), userEntity.getEmail());
            userEntity.setWallet(wallet);
            userDao.persist(userEntity);
        }
        httpSession.setAttribute("userEntity", userEntity);

        return "done.html";
    }


}
