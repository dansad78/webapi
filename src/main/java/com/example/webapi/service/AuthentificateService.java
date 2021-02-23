package com.example.webapi.service;

import com.example.webapi.component.JwtTokenUtil;
import com.example.webapi.dao.ApiUserDao;
import com.example.webapi.dao.TransactionDao;
import com.example.webapi.entity.ApiUser;
import com.example.webapi.entity.ApiUserDetails;
import com.example.webapi.entity.Message;
import com.example.webapi.entity.Transaction;
import com.example.webapi.enums.ErrorTypes;
import com.example.webapi.model.Errors;
import com.example.webapi.model.RequestJwt;
import com.example.webapi.model.ResponseJwt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class AuthentificateService {
    @Autowired
    private ApiUserDao dao;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private TransactionDao transactionDao;


    public AuthentificateService() {
    }


    public ResponseEntity<?> getJWTToken(String json){
        HttpHeaders responseHeaders = new HttpHeaders();
        RequestJwt request = null;
        String errors = null;
        String token = new String();
        if(json != null && !json.isEmpty()){
            try {
                request = new ObjectMapper().readValue(json, RequestJwt.class);
                if(request.getUsername() == null || request.getUsername().isEmpty()){
                    System.out.println(request.getUsername());
                    errors = ErrorTypes.NOUSERNAME.getTitle();
                }
                if(request.getPassword() == null || request.getPassword().isEmpty()){
                    errors = ErrorTypes.NOPASSWORD.getTitle();
                }
                BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder();
                if(!request.getUsername().equals(dao.findFirstByUsername(request.getUsername()).getUsername()) /*|| !encoder2.matches(request.getPassword(), dao.findFirstByUsername(request.getUsername()).getPassword())*/){
                    errors = ErrorTypes.NOUSERINDATABASE.getTitle();
                } else {
                    try {
                        Authentication authenticate = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
                        );
                        final ApiUserDetails userDetails = (ApiUserDetails) userDetailsService
                                .loadUserByUsername(request.getUsername());
                        token = jwtTokenUtil.generateToken(userDetails);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if(token != null){
                        System.out.println(token);
                    }
                }
            } catch (JsonProcessingException e) {
                errors = ErrorTypes.NOREQUESTBODY.getTitle();
                request = null;
                e.printStackTrace();
            }
        } else {
            errors = ErrorTypes.NOREQUESTBODY.getTitle();
            request = null;
        }


        ResponseEntity<?> response = null;
        response = (errors == null) ? new ResponseEntity<>(new ResponseJwt(token), responseHeaders, HttpStatus.OK) : new ResponseEntity<>(new Errors(errors), responseHeaders, HttpStatus.BAD_REQUEST);
        return response;
    }

    public ResponseEntity<?> makePayment(String authorization){
        String response = null;
        HttpStatus status = null;
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenValue = authorization.replace("Bearer ", "").trim();
            String username = new JwtTokenUtil().extractUsername(tokenValue);
            Transaction transaction = transactionDao.getFirstByApiUser_UsernameOrderByDateDesc(username);
            BigDecimal standartAmount = new BigDecimal(1.1);
            if(transaction.getAmount().compareTo(standartAmount) > 0) {
                BigDecimal amount = transaction.getAmount().subtract(standartAmount).setScale(2, RoundingMode.HALF_EVEN);
                Transaction transaction2 = new Transaction(LocalDateTime.now(), transaction.getApiUser(), amount);
                transactionDao.save(transaction2);
                response = "На вашем счету осталось " + amount + " USD";
            } else {
                response = "На вашем счету сумма меньше требуемой для проведения транзакции " + transaction.getAmount() + " USD. Пополните счет для проведения операций";
            }
            status = HttpStatus.OK;

        } else {
            response = "Invalid Token";
            status = HttpStatus.PROXY_AUTHENTICATION_REQUIRED;
        }
        return new ResponseEntity<>(new Message(response),status);
    }
}
