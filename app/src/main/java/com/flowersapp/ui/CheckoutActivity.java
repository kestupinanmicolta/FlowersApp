package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToOrderSucess;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.flowersapp.R;
import com.flowersapp.data.model.User;

public class CheckoutActivity extends AppCompatActivity {

    private EditText etAddress;
    private Button btnConfirmOrder;
    private User user;
    private final String canalId = "confirmacion_pedido";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        createNotificationChannel();
        initViews();
        setupListener();
    }

    private void initViews() {
        etAddress = findViewById(R.id.etAddress);
        btnConfirmOrder = findViewById(R.id.btnConfirmOrder);
        user = (User) getIntent().getSerializableExtra("user");

        // Cargar la dirección si el usuario existe
        if (user != null && user.getAdress() != null) {
            etAddress.setText(user.getAdress());
        }
    }

    private void setupListener() {
        etAddress.setText(user.getAdress());
        btnConfirmOrder.setOnClickListener(v -> {
            String address = etAddress.getText().toString().trim();
            String nombreUsuario = (user != null && user.getName() != null) ? user.getName() : "Cliente";

            if (address.isEmpty()) {
                etAddress.setError("Por favor ingresa una dirección");
                return;
            }

// 1. Inmediata: Pedido Confirmado
            enviarNotificacionSecuencial("¡Pedido Confirmado! 🌸",
                    "¡Hola " + nombreUsuario + "! Estamos preparando tus flores.", 1, false);

// 2. A los 60 segundos: En camino
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                enviarNotificacionSecuencial("Pedido en camino 🚚",
                        nombreUsuario + ", tu repartidor ya salió con el pedido.", 2, false);
            }, 60000);

// 3. A los 120 segundos: Entregado
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                enviarNotificacionSecuencial("¡Pedido Entregado! ✨",
                        "¡Listo, " + nombreUsuario + "! Disfruta tus flores.", 3, true);
            }, 120000);

            if (user != null && user.getCar() != null) {
                user.getCar().clear();
            }

            goToOrderSucess(this, user);
            finish();
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Confirmación de Pedidos";
            String description = "Seguimiento de pedidos en tiempo real";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(canalId, name, importance);
            channel.setDescription(description);
            channel.enableVibration(true);
            // Patrón: pausa, vibración larga, pausa, vibración corta
            channel.setVibrationPattern(new long[]{0, 500, 200, 500});

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void enviarNotificacionSecuencial(String titulo, String mensaje, int id, boolean esEntrega) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, canalId)
                .setSmallIcon(R.drawable.ic_flower)
                .setContentTitle(titulo)
                .setContentText(mensaje)
                // Esto permite que el texto se expanda si es largo
                .setStyle(new NotificationCompat.BigTextStyle().bigText(mensaje))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // Si es la entrega final, forzamos el sonido y vibración por defecto
        if (esEntrega) {
            builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(id, builder.build());
    }
}