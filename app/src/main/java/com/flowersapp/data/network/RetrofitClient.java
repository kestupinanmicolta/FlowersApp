package com.flowersapp.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    // URL Base de la API de Colombia
    private static final String BASE_URL = "https://api-colombia.com/api/v1/";
    private static RetrofitClient instance;
    private final Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Usa la librería GSON
                .build();
    }

    // Método Singleton para obtener la instancia única
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    // Método para obtener la interfaz que creamos antes
    public LocationApiService getApi() {
        return retrofit.create(LocationApiService.class);
    }
}