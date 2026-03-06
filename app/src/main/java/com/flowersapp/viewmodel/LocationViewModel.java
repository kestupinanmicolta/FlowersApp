package com.flowersapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowersapp.data.model.City;
import com.flowersapp.data.model.Department;

import java.util.List;

import androidx.lifecycle.LiveData;

import com.flowersapp.data.repository.LocationRepository;

public class LocationViewModel extends ViewModel {
    private final LocationRepository repository;

    // Usamos MutableLiveData final para que la Activity siempre observe el mismo objeto
    private final MutableLiveData<List<Department>> departments = new MutableLiveData<>();
    private final MutableLiveData<List<City>> cities = new MutableLiveData<>();

    public LocationViewModel() {
        repository = new LocationRepository();
        loadDepartments();
    }

    private void loadDepartments() {
        // Pasamos el MutableLiveData al repositorio para que él lo llene
        repository.getDepartments(departments);
    }

    public void loadCities(int departmentId) {
        // Pasamos el MutableLiveData de ciudades para que el repositorio lo actualice
        repository.getCitiesByDepartment(departmentId, cities);
    }

    public LiveData<List<Department>> getDepartments() {
        return departments;
    }

    public LiveData<List<City>> getCities() {
        return cities;
    }
}