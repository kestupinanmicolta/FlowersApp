package com.flowersapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowersapp.data.repository.UserRepository;

public class ForgotPasswordViewModel extends ViewModel {
    private MutableLiveData<Boolean> forgotResult = new MutableLiveData<>();
    public LiveData<Boolean> getForgotResult() {
        return forgotResult;
    }

    public void emailExist(String email) {
        forgotResult.setValue(UserRepository.isRegistered(email));
    }
}