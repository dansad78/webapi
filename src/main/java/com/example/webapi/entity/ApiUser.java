package com.example.webapi.entity;



import javax.persistence.*;

@Entity
@Table(name = "api_user")
public class ApiUser {
    @Id
    @SequenceGenerator(name = "api_user_seq", sequenceName = "api_user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_user_seq")
    Integer id;
    @Column(unique = true)
    String username;
    @Column
    String password;

    public ApiUser() {
    }

    public ApiUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "apiUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
