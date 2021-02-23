package com.example.webapi.dao;

import com.example.webapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction, Integer> {
    Transaction getFirstByApiUser_UsernameOrderByDateDesc(String username);
}
