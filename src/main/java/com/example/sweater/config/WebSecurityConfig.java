package com.example.sweater.config;

import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //чтобы аннотация проверки прав работала
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //авторизация
                    .antMatchers("/", "/registration").permitAll() //разрешаем полный доступ
                    .anyRequest().authenticated() //для остальных запросов- авторизация
                .and()
                    .formLogin() //включаем форму логина
                    .loginPage("/login") //указываем страницу
                    .permitAll() //разрешаем пользоваться всем
                .and()
                    .logout() //вкл форму выхода
                    .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService) //нужен для того, чтобы менеджер мог ходить в БД и искать пользователей
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}