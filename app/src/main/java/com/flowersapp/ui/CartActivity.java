package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToCatalog;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flowersapp.R;
import com.flowersapp.adapters.CarAdapter;
import com.flowersapp.data.model.Car;
import com.flowersapp.viewmodel.CatalogViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CartActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private CatalogViewModel viewModel;
    private CarAdapter adapter;
    private TextView tvTotal;
    private RecyclerView rvCartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initViews();
        setupListener();
        configurarRecyclerView();
    }

    public void initViews() {
        btnBack = findViewById(R.id.btnBack);
        rvCartItems = findViewById(R.id.rvCartItems);
        tvTotal= findViewById(R.id.tvTotal);
    }

    public void setupListener() {
        btnBack.setOnClickListener(a -> goToCatalog(this));
    }

    private void configurarRecyclerView() {
        List<Car> lista = new ArrayList<>();
        lista.add(new Car("Ramo Amor Eterno", 45000.0,2, R.drawable.ic_flowersmix));
        lista.add(new Car("Tulipanes de Colores", 4000.0, 3, R.drawable.ic_flowerpink));
        lista.add(new Car("Rosas rojas", 4000.0, 4, R.drawable.ic_flowersred));
        double total = 0;

        for (Car item : lista) {
            // Multiplicamos precio por cantidad de cada objeto Car
            total += item.getPrice() * item.getCantidad();
        }

// Para mostrarlo con el formato de moneda que ya definimos
        DecimalFormat formatter = new DecimalFormat("$ #,###");
        tvTotal.setText("Total: " + formatter.format(total));
        rvCartItems.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CarAdapter(lista);
        rvCartItems.setAdapter(adapter);
    }

}