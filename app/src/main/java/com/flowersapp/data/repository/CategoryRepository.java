package com.flowersapp.data.repository;

import com.flowersapp.data.model.Catalog;
import com.flowersapp.data.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private static List<Category> typeFlowerList = new ArrayList<>();

    static {
        typeFlowerList.add(new Category(0, "Todas"));
        typeFlowerList.add(new Category(1, "Rosas"));
        typeFlowerList.add(new Category(2, "Tulipanes"));
        typeFlowerList.add(new Category(3, "Especiales"));
        typeFlowerList.add(new Category(4, "Hortensias"));
    }
    public static List<Category> getCategory() {
        return typeFlowerList;
    }
    public static Category getCategory(int id) {
        return typeFlowerList.stream()
                .filter(type -> type.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
