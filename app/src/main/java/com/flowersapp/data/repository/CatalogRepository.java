package com.flowersapp.data.repository;

import com.flowersapp.R;
import com.flowersapp.data.model.Catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogRepository {
    private static List<Catalog> lista = new ArrayList<>();

    static {
        lista.add(new Catalog(1,1,"Ramo Amor Eterno", 45000.0, R.drawable.ic_flowersmix,"Flor bonita"));
        lista.add(new Catalog(2,1,"Tulipanes de Colores", 4000.0,  R.drawable.ic_flowerpink,"Flor bonita"));
        lista.add(new Catalog(3,2,"Rosas rojas", 4000.0,  R.drawable.ic_flowersred,"Flor bonita"));
        lista.add(new Catalog(4,3,"Caja floral elegancia", 4000.0,  R.drawable.ic_flowersyellow,"Flor bonita"));
        lista.add(new Catalog(5,4,"Ramo Amor Eterno", 45000.0, R.drawable.ic_flowersmix,"Flor bonita"));
        lista.add(new Catalog(6,4,"Tulipanes de Colores", 4000.0,  R.drawable.ic_flowerpink,"Flor bonita"));
        lista.add(new Catalog(7,5,"Rosas rojas", 4000.0,  R.drawable.ic_flowersred,"Flor bonita"));
        lista.add(new Catalog(8,2,"Caja floral elegancia", 4000.0,  R.drawable.ic_flowersyellow,"Flor bonita"));
    }
    public static List<Catalog> getCatalog() {
        return lista;
    }
    public static List<Catalog> filterByCategory(int typeId) {
        return lista.stream()
                .filter(flower -> flower.getCategory() == typeId)
                .collect(Collectors.toList());
    }
}
