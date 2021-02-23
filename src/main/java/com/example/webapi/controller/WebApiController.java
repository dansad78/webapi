package com.example.webapi.controller;

import com.example.webapi.component.JwtTokenUtil;
import com.example.webapi.dao.ApiUserDao;
import com.example.webapi.dao.TransactionDao;
import com.example.webapi.entity.ApiUser;
import com.example.webapi.entity.Message;
import com.example.webapi.entity.Transaction;
import com.example.webapi.service.AuthentificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;


@RestController
public class WebApiController {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    ApiUserDao dao;
    @Autowired
    AuthentificateService authentificateService;
    @Autowired
    TransactionDao transactionDao;


    @RequestMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getToken(@RequestBody String json){
        /*ApiUser user = dao.findFirstByUsername("admin");
        if(user == null) {
            ApiUser user2 = new ApiUser();
            user2.setUsername("admin");
            user2.setPassword(encoder.encode("123456"));
            dao.save(user2);
        } else {
            user.setPassword(encoder.encode("123456"));
            dao.save(user);
        }*/
       return authentificateService.getJWTToken(json);

    }
    @GetMapping(path = "/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            SecurityContextHolder.getContext().setAuthentication(null);

            return ResponseEntity.ok("");
        }

    @GetMapping(path="/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPayment(@RequestHeader(name="Authorization", required = false) String authorization) {
           return authentificateService.makePayment(authorization);
    }



}
