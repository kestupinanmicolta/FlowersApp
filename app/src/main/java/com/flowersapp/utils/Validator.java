package com.flowersapp.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class Validator {
    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }
    public static boolean isFieldEmpty(String text) {
        return TextUtils.isEmpty(text) || text.trim().isEmpty();
    }
    public static boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && phone.length() >= 7 && TextUtils.isDigitsOnly(phone);
    }
    public static boolean doPasswordsMatch(String pass1, String pass2) {
        if (pass1 == null || pass2 == null) return false;
        return pass1.equals(pass2);
    }
}