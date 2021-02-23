package com.example.webapi;

import antlr.BaseAST;
import com.example.webapi.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories("com.example.webapi.dao")
public class WebApiApplication {
    @Autowired
    InitService initService;

    public static void main(String[] args) {

        SpringApplication.run(WebApiApplication.class, args);


    }

    @Bean
    public void init(){
        initService.init();
    }

}
