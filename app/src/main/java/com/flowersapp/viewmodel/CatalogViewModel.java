package com.flowersapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.flowersapp.data.model.Catalog;
import com.flowersapp.data.model.Category;
import com.flowersapp.data.repository.CatalogRepository;
import com.flowersapp.data.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogViewModel extends ViewModel {
    private MutableLiveData<List<Catalog>> catalog;
    private MutableLiveData<List<Category>> category;

    // EL CONSTRUCTOR DEBE SER ASÍ (VACÍO)
    public CatalogViewModel() {
        // No recibimos parámetros aquí
    }

    public LiveData<List<Catalog>> getCatalog() {
        if (catalog == null) {
            catalog = new MutableLiveData<>();
            catalog.setValue(CatalogRepository.getCatalog());
        }
        return catalog;
    }

    public LiveData<List<Category>> getCategory() {
        if (category == null) {
            category = new MutableLiveData<>();
            category.setValue(CategoryRepository.getCategory());
        }
        return category;
    }

    public void filterFlowersByCategory(int categoryId) {
        List<Catalog> fullList = CatalogRepository.getCatalog();
        if (categoryId == 0) {
            // Si es 0 ("Todas"), mandamos la lista completa
            catalog.setValue(fullList);
        } else {
            // Si es cualquier otro ID, filtramos usando Java Streams
            List<Catalog> filteredList = fullList.stream()
                    .filter(item -> item.getCategory() == categoryId)
                    .collect(Collectors.toList());
            catalog.setValue(filteredList);
        }
    }
}