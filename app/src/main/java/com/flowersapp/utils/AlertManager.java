package com.flowersapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.flowersapp.R;

public class AlertManager {
    public interface AlertCallback {
        void onConfirm();
    }

    public void mostrarAlerta(String mensaje, Activity activity, boolean finalizarActividad) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        // 1. Inflar la vista PRIMERO
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
        builder.setView(dialogView);

        // 2. Ahora sí, buscar los IDs dentro de dialogView
        ImageView icon = dialogView.findViewById(R.id.alertIcon);
        TextView tvMessage = dialogView.findViewById(R.id.alertMessage);
        Button btnOk = dialogView.findViewById(R.id.btnAlertOk);

        // 3. Configurar los elementos
        if (icon != null) icon.setImageResource(R.drawable.ic_flower);
        tvMessage.setText(mensaje);

        final AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        btnOk.setOnClickListener(v -> {
            dialog.dismiss();
            if (finalizarActividad) {
                activity.finish();
            }
        });

        dialog.show();
    }

    public void mostrarConfirmacion(String mensaje, Activity activity, AlertCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        // Usamos el layout de confirmación (el que tiene dos botones)
        View dialogView = activity.getLayoutInflater().inflate(R.layout.custom_confirm_dialog, null);
        builder.setView(dialogView);

        TextView tvMessage = dialogView.findViewById(R.id.alertMessage);
        ImageView icon = dialogView.findViewById(R.id.alertIcon);
        Button btnSi = dialogView.findViewById(R.id.btnConfirm);
        Button btnNo = dialogView.findViewById(R.id.btnCancel);

        if (icon != null) icon.setImageResource(R.drawable.ic_flower);
        tvMessage.setText(mensaje);

        final AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        btnSi.setOnClickListener(v -> {
            if (callback != null) callback.onConfirm();
            dialog.dismiss();
        });

        btnNo.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}