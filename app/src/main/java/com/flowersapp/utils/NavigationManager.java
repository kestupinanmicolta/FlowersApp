package com.flowersapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.flowersapp.data.model.User;
import com.flowersapp.ui.CheckoutActivity;
import com.flowersapp.ui.OrderSucessActivity;
import com.flowersapp.ui.ProfileActivity;
import com.flowersapp.ui.ForgotPasswordActivity;
import com.flowersapp.ui.LoginActivity;
import com.flowersapp.ui.RegisterActivity;
import com.flowersapp.data.model.Catalog;
import com.flowersapp.ui.CartActivity;
import com.flowersapp.ui.CatalogActivity;
import com.flowersapp.ui.ProductDetailActivity;

public class NavigationManager {

    public static void goToCatalog(Activity activity, User user) {
        Intent intent = new Intent(activity, CatalogActivity.class);
        intent.putExtra("user", user);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    public static void goToCart(Activity activity, User user) {
        Intent intent = new Intent(activity, CartActivity.class);
        intent.putExtra("user", user);
        activity.startActivity(intent);
    }

    public static void goToProductDetail(Context context, Catalog catalog, User user) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("catalog", catalog);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    public static void goToUserRegister(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    public static void goToForgotPassword(Activity activity) {
        Intent intent = new Intent(activity, ForgotPasswordActivity.class);
        activity.startActivity(intent);
    }

    public static void goToLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    public static void goToProfile(Activity activity, User user) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra("user", user);
        activity.startActivity(intent);
    }

    public static void goToWhatsapp(Activity activity, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }
    public static void goToOrderSucess(Activity activity, User user) {
        Intent intent = new Intent(activity, OrderSucessActivity.class);
        intent.putExtra("user", user); // Pasamos el usuario para no perder la sesión
        activity.startActivity(intent);
    }
    public static void goToCheckout(Activity activity, User user) {
        Intent intent = new Intent(activity, CheckoutActivity.class);
        intent.putExtra("user", user); // Pasamos el usuario para no perder la sesión
        activity.startActivity(intent);
    }
}
