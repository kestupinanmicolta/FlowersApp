package com.flowersapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowersapp.data.repository.LoginRepository;

public class LoginViewModel extends ViewModel {
    private LoginRepository repository = new LoginRepository();
    private MutableLiveData<Boolean> loginResult = new MutableLiveData<>();

    public LiveData<Boolean>    getLoginResult() {
        return loginResult;
    }

    public void realizarLogin(String email, String password) {
        // El ViewModel le delega la responsabilidad al Repositorio
        boolean isValid = repository.verifyCredentials(email, password);
        loginResult.setValue(isValid);
    }
}
