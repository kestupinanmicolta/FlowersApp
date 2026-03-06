package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToCart;
import static com.flowersapp.utils.NavigationManager.goToProfile;
import static com.flowersapp.utils.NavigationManager.goToWhatsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flowersapp.R;
import com.flowersapp.adapters.CatalogAdapter;
import com.flowersapp.adapters.CategoryAdapter;
import com.flowersapp.data.model.User;
import com.flowersapp.data.repository.CategoryRepository;
import com.flowersapp.utils.AlertManager;
import com.flowersapp.viewmodel.CatalogViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.URLEncoder;
import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {
    private FrameLayout layoutCart, layoutProfile;
    private RecyclerView rvCatalog, rvCategories;
    private CatalogViewModel viewModel;
    private CatalogAdapter adapter;
    private User user;
    private FloatingActionButton fabSupport;
    private String urlWhatsapp = "https://api.whatsapp.com/send?phone=";
    private String numero = "573194701032"; // Pon tu número aquí
    private String mensaje = "Hola FlorAbby, tengo una consulta sobre el catálogo.";
    private AlertManager alertManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        initViews();
        configurarViewModel(); // 1. Primero el ViewModel
        setupCategoryRecycler(); // 2. Luego el Recycler que lo usa
        setupListener();
        animarBotonAyuda();
    }

    public void initViews() {
        layoutCart = findViewById(R.id.layoutCart);
        rvCatalog = findViewById(R.id.rvCatalog);
        layoutProfile = findViewById(R.id.layoutProfile);
        rvCategories = findViewById(R.id.rvCategories);
        rvCatalog.setLayoutManager(new GridLayoutManager(this, 2));
        user = (User) getIntent().getSerializableExtra("user");
        adapter = new CatalogAdapter(new ArrayList<>(), user);
        rvCatalog.setAdapter(adapter);
        fabSupport = findViewById(R.id.fabSupport);
        alertManager = new AlertManager();
    }

    private void configurarViewModel() {
        viewModel = new ViewModelProvider(this).get(CatalogViewModel.class);
        viewModel.getCatalog().observe(this, catalogs -> {
            if (catalogs != null) {
                adapter.updateList(catalogs);
            }
        });
    }

    public void setupListener() {
        layoutCart.setOnClickListener(a -> goToCart(this, user));
        layoutProfile.setOnClickListener(a -> goToProfile(this, user));
        fabSupport.setOnClickListener(v -> contactarSoporte());
    }

    public void setupCategoryRecycler() {
        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        CategoryAdapter categoryAdapter = new CategoryAdapter(CategoryRepository.getCategory(), type -> {
            viewModel.filterFlowersByCategory(type.getId());
        });

        rvCategories.setAdapter(categoryAdapter);
    }

    private void contactarSoporte() {
        try {
            String url = urlWhatsapp + numero + "&text=" + URLEncoder.encode(mensaje, "UTF-8");
            goToWhatsapp(this, url);
        } catch (Exception e) {
            alertManager.mostrarAlerta("No se pudo abrir WhatsApp", this, false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (user != null && user.getCar() != null) {
            int totalItems = user.getCar().size();
            TextView tvCartBadge = findViewById(R.id.tvCartBadge);
            if (totalItems > 0) {
                tvCartBadge.setVisibility(View.VISIBLE);
                tvCartBadge.setText(String.valueOf(totalItems));
            } else {
                tvCartBadge.setVisibility(View.GONE);
            }
        }
    }
    private void animarBotonAyuda() {
        // Una pequeña animación de escala para llamar la atención
        fabSupport.setScaleX(0f);
        fabSupport.setScaleY(0f);

        fabSupport.animate()
                .scaleX(1.1f)
                .scaleY(1.1f)
                .setDuration(500)
                .withEndAction(() -> {
                    fabSupport.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
                }).start();
    }
}