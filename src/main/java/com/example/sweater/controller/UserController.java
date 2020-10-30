package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user") //реквест маппинг на уровне класса, чтобы у каждого метода это не писать
@PreAuthorize("hasAuthority('ADMIN')") //эта аннотация проверяем перед каждым методом наличие у пользователя прав
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    //маппинг редактора пользователя
    @GetMapping("{user}")
    public String userEditForum(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user //по RequestParam будем получать юзера из БД
    ) {
        //устанавливаем имя
        user.setUsername(username);
        //получаем список ролей
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        //очищаем роли пользователя
        user.getRoles().clear();

        //проверяем, содержит ли форма роли для пользователя
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));//если да, то добавляем
            }
        }
        //сохраняем имя в бд
        userRepo.save(user);
        return "redirect:/user";
    }
}
