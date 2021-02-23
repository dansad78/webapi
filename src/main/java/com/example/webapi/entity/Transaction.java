package com.example.webapi.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
public class Transaction implements Serializable {
    @Id
    @SequenceGenerator(name = "transactions_seq", sequenceName = "transactions_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_seq")
    Integer id;
    @Column
    LocalDateTime date;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="api_user", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    ApiUser apiUser;
    @Column
    BigDecimal amount;

    public Transaction() {
    }

    public Transaction(LocalDateTime date, ApiUser apiUser, BigDecimal amount) {
        this.date = date;
        this.apiUser = apiUser;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ApiUser getApiUser() {
        return apiUser;
    }

    public void setApiUser(ApiUser apiUser) {
        this.apiUser = apiUser;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
