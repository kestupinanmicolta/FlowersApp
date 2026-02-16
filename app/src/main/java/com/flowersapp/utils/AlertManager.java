package com.flowersapp.utils;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

public class AlertManager {
    public void mostrarAlerta(String mensaje, Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle("Atención")
                .setMessage(mensaje)
                .setPositiveButton("Entendido", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
