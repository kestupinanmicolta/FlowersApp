package com.flowersapp.data.network;

import com.flowersapp.data.model.City;
import com.flowersapp.data.model.Department;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Path;

public interface LocationApiService {

    // Obtiene la lista completa de departamentos de Colombia
    @GET("Department")
    Call<List<Department>> getDepartments();

    // Obtiene las ciudades filtradas por el ID del departamento seleccionado
    @GET("Department/{id}/cities")
    Call<List<City>> getCitiesByDepartment(@Path("id") int departmentId);
}
