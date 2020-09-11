package com.example.paiprojekt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.websocket.OnError;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    @NotNull(message = "Imie nie może być puste!")
    @Size(message = "Długość imienia musi być pomiędzy 3 i 64!", min = 3, max = 64)
    @Pattern(message = "Imie może się składać tylko z liter(bez znaków polskich)!", regexp = "[a-zA-Z]*")
    private String name;

    @NotNull(message = "Nazwisko nie może być puste!")
    @Size(message = "Długość nazwiska musi być pomiędzy 2 i 64!", min = 2, max = 64)
    @Pattern(message = "Nazwisko może się składać tylko z liter(bez znaków polskich)!", regexp = "[a-zA-Z]*")
    private String surname;

    @NotNull(message = "Nazwa użytkownika nie może być pusta!")
    @Size(message = "Długość nazwy użytkownika musi być pomiędzy 5 i 45!", min = 5, max = 45)
    @Pattern(message = "Nazwa użytkownika może się składać tylko z liter(bez znaków polskich) oraz myślnika i podkreślinika", regexp = "[a-zA-Z\\d\\-_]*")
    private String login;

    @NotNull(message = "Hasło nie może być puste!")
    @Size(message = "Długości hasła musi być pomiędzy 8 i 64!", min = 8, max = 64)
    @Pattern(message = "Hasło nie może zawierać znaków białych!", regexp = "[\\S]*")
    private String password;

    public User() {
    }

    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
