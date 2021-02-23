package com.example.webapi.enums;

public enum ErrorTypes {
    NOREQUESTBODY("Нет тела запроса"),
    NOUSERNAME("Не введен username"),
    NOPASSWORD("Не введен пароль"),
    NOUSERINDATABASE("Пользователь с таким именем или паролем не существует");
    private String title;

    ErrorTypes(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
