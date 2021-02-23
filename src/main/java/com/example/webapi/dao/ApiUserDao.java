package com.example.webapi.dao;

import com.example.webapi.entity.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ApiUserDao extends JpaRepository<ApiUser, Integer> {
    ApiUser findFirstByUsername(String username);
}
