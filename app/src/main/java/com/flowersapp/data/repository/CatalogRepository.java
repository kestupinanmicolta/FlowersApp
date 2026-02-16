package com.flowersapp.data.repository;

import com.flowersapp.R;
import com.flowersapp.data.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogRepository {
    public List<Catalog> getCatalog() {
        List<Catalog> lista = new ArrayList<>();
        lista.add(new Catalog("Ramo Amor Eterno", 45000.0, R.drawable.ic_flowersmix));
        lista.add(new Catalog("Tulipanes de Colores", 4000.0,  R.drawable.ic_flowerpink));
        lista.add(new Catalog("Rosas rojas", 4000.0,  R.drawable.ic_flowersred));
        lista.add(new Catalog("Caja floral elegancia", 4000.0,  R.drawable.ic_flowersyellow));
        lista.add(new Catalog("Ramo Amor Eterno", 45000.0, R.drawable.ic_flowersmix));
        lista.add(new Catalog("Tulipanes de Colores", 4000.0,  R.drawable.ic_flowerpink));
        lista.add(new Catalog("Rosas rojas", 4000.0,  R.drawable.ic_flowersred));
        lista.add(new Catalog("Caja floral elegancia", 4000.0,  R.drawable.ic_flowersyellow));
        return lista;
    }
}
