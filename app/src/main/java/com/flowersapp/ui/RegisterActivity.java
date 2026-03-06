package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToCatalog;
import static com.flowersapp.utils.NavigationManager.goToLogin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.flowersapp.R;
import com.flowersapp.data.model.City;
import com.flowersapp.data.model.Department;
import com.flowersapp.data.model.User;
import com.flowersapp.utils.AlertManager;
import com.flowersapp.utils.Validator;
import com.flowersapp.viewmodel.LocationViewModel;
import com.flowersapp.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private EditText etFullName, etEmail, etPhone, etPassRegister, etConfirmPass, etAddress;
    private Button btnConfirmRegister;
    private AlertManager alertManager;
    private TextView tvBackToLogin;
    private RegisterViewModel viewModel;
    private LocationViewModel locationViewModel; // NUEVO
    private User user;
    private Spinner spinnerDepartment, spinnerCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        setupObservers();
        setupListener();
    }

    public void initViews() {
        btnBack = findViewById(R.id.btnBack);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmailRegister);
        etPhone = findViewById(R.id.etPhone);
        etPassRegister = findViewById(R.id.etPassRegister);
        etConfirmPass = findViewById(R.id.etConfirmPass);
        btnConfirmRegister = findViewById(R.id.btnConfirmRegister);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);
        etAddress = findViewById(R.id.etAddress);
        spinnerDepartment = findViewById(R.id.spinnerDepartment);
        spinnerCity = findViewById(R.id.spinnerCity);

        alertManager = new AlertManager();
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class); // Inicializar
    }

    public void setupListener() {
        btnBack.setOnClickListener(a -> goToLogin(this));
        tvBackToLogin.setOnClickListener(a -> goToLogin(this));
        spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Department selectedDept = (Department) parent.getSelectedItem();
                locationViewModel.loadCities(selectedDept.getId()); // Cargar ciudades del depto
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        btnConfirmRegister.setOnClickListener(a -> {
            String name = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String pass = etPassRegister.getText().toString().trim();
            String confirmPass = etConfirmPass.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            Department dept = (Department) spinnerDepartment.getSelectedItem();
            City city = (City) spinnerCity.getSelectedItem();
            if (validateForm(name, email, phone, address, pass, confirmPass)) {
                user = new User(name, email, phone, pass, address, city, dept);
                viewModel.registrarUsuario(user);
            }
        });
    }
    public void setupObservers() {
        // Observar Departamentos para llenar el primer Spinner
        locationViewModel.getDepartments().observe(this, departments -> {
            if (departments != null) {
                ArrayAdapter<Department> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, departments);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDepartment.setAdapter(adapter);
            }
        });

        // Observar Ciudades para llenar el segundo Spinner
        locationViewModel.getCities().observe(this, cities -> {
            if (cities != null) {
                ArrayAdapter<City> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, cities);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCity.setAdapter(adapter);
            }
        });

        viewModel.getRegisterResult().observe(this, fueExitoso -> {
            if (fueExitoso != null && fueExitoso) {
                goToCatalog(this, user);
                finish();
            } else if (fueExitoso != null) {
                alertManager.mostrarAlerta("Error en el registro.", this, false);
            }
        });
    }

    private boolean validateForm(String nombre, String email, String tel, String direccion, String pass, String confirmPass) {
        if (Validator.isFieldEmpty(nombre) || Validator.isFieldEmpty(direccion)) {
            alertManager.mostrarAlerta("Completa todos los campos", this, false);
            return false;
        }
        if (spinnerCity.getSelectedItem() == null) {
            alertManager.mostrarAlerta("Selecciona una ciudad", this, false);
            return false;
        }
        if (Validator.isFieldEmpty(nombre)) {
            alertManager.mostrarAlerta("Escribe tu nombre", this, false);
            return false;
        }
        if (!Validator.isValidEmail(email)) {
            alertManager.mostrarAlerta("Correo inválido", this, false);
            return false;
        }
        if (!Validator.isValidPhone(tel)) {
            alertManager.mostrarAlerta("Teléfono inválido (mínimo 7 dígitos)", this, false);
            return false;
        }
        if (!Validator.isValidPassword(pass)) {
            alertManager.mostrarAlerta("La contraseña debe tener 6+ caracteres", this, false);
            return false;
        }
        if (!Validator.doPasswordsMatch(pass, confirmPass)) {
            alertManager.mostrarAlerta("Las contraseñas no coinciden", this, false);
            return false;
        }
        return true;
    }
}
