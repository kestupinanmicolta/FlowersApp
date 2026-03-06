package com.flowersapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.flowersapp.data.model.City; // Debes crear estos modelos
import com.flowersapp.data.model.Department;
import com.flowersapp.data.network.LocationApiService;
import com.flowersapp.data.network.RetrofitClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {
    private final LocationApiService apiService;

    public LocationRepository() {
        // Suponiendo que ya creaste una clase RetrofitClient
        this.apiService = RetrofitClient.getInstance().getApi();
    }

    // Método para obtener Departamentos
    public void getDepartments(MutableLiveData<List<Department>> data) {
        apiService.getDepartments().enqueue(new Callback<List<Department>>() {
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body()); // Aquí actualizamos el contenido, no el objeto
                }
            }
            @Override
            public void onFailure(Call<List<Department>> call, Throwable t) {
                data.setValue(null);
            }
        });
    }

    // Método para obtener Ciudades por ID de departamento
    public void getCitiesByDepartment(int departmentId, MutableLiveData<List<City>> data) {
        apiService.getCitiesByDepartment(departmentId).enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                data.setValue(null);
            }
        });
    }
}
