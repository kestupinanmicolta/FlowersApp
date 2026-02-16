package com.flowersapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowersapp.data.model.Catalog;
import com.flowersapp.data.repository.CatalogRepository;

import java.util.List;

public class CatalogViewModel extends ViewModel {
    private MutableLiveData<List<Catalog>> catalog;
    private final CatalogRepository repository;

    public CatalogViewModel() {
        repository = new CatalogRepository();
    }

    public LiveData<List<Catalog>> getCatalog() {
        if (catalog == null) {
            catalog = new MutableLiveData<>();
            // Obtenemos los datos del repo (ej. Ramo Amor Eterno, Rosas Rojas)
            catalog.setValue(repository.getCatalog());
        }
        return catalog;
    }
}

