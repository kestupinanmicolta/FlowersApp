package com.flowersapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowersapp.data.model.User;
import com.flowersapp.data.repository.UserRepository;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<User> loginResult = new MutableLiveData<>();
    public LiveData<User> getLoginResult() {
        return loginResult;
    }
    public void realizarLogin(String email, String pass) {
        User user = UserRepository.login(email, pass);
        if (user != null) {
            loginResult.setValue(user);
        } else {
            loginResult.setValue(null);
        }
    }
}
