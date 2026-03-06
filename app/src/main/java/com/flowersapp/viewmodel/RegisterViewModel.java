package com.flowersapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowersapp.data.model.User;
import com.flowersapp.data.repository.UserRepository;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<Boolean> registerResult = new MutableLiveData<>();

    public LiveData<Boolean> getRegisterResult() {
        return registerResult;
    }

    public void registrarUsuario(User nuevo) {
        if (UserRepository.isRegistered(nuevo.getEmail())) {
            registerResult.setValue(false);
        } else {
            UserRepository.addUser(nuevo);
            registerResult.setValue(true);
        }
    }
}