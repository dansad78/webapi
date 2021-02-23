package com.example.webapi.service;

import com.example.webapi.dao.ApiUserDao;
import com.example.webapi.dao.TransactionDao;
import com.example.webapi.entity.ApiUser;
import com.example.webapi.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {
    @Autowired
    private ApiUserDao dao;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TransactionDao transactionDao;
    public void init(){
        List<ApiUser> apiUsers = new ArrayList<>();
        apiUsers.add(new ApiUser("admin", encoder.encode("123456")));
        apiUsers.add(new ApiUser("admin2",encoder.encode("123456")));
        dao.saveAll(apiUsers);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(LocalDateTime.now(), dao.findFirstByUsername("admin"), new BigDecimal(8)));
        transactions.add(new Transaction(LocalDateTime.now(), dao.findFirstByUsername("admin2"), new BigDecimal(8)));
        transactionDao.saveAll(transactions);
    }


}
