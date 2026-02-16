package com.flowersapp.data.repository;

public class LoginRepository {
    public boolean verifyCredentials(String email, String password) {
        return email.equals("kestupinanmicolta@gmail.com") && password.equals("123456");
    }
}
