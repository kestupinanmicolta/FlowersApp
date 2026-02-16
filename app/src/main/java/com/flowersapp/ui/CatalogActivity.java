package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToCart;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flowersapp.R;
import com.flowersapp.adapters.CatalogAdapter;
import com.flowersapp.data.model.Catalog;
import com.flowersapp.viewmodel.CatalogViewModel;

import java.util.List;

public class CatalogActivity extends AppCompatActivity {
    private FrameLayout layoutCart;
    private RecyclerView rvCatalog;
    private CatalogViewModel viewModel;
    private CatalogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        initViews();
        configurarViewModel();
        setupListener();
    }

    public void initViews() {
        layoutCart = findViewById(R.id.layoutCart);
        rvCatalog = findViewById(R.id.rvCatalog);
        rvCatalog.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void configurarViewModel() {
        viewModel = new ViewModelProvider(this).get(CatalogViewModel.class);
        viewModel.getCatalog().observe(this, catalogs -> {
            if (catalogs != null) {
                configurarRecyclerView(catalogs);
            }
        });
    }

    private void configurarRecyclerView(List<Catalog> lista) {
        adapter = new CatalogAdapter(lista);
        rvCatalog.setAdapter(adapter);
    }

    public void setupListener() {
        layoutCart.setOnClickListener(a -> goToCart(this));
    }
}