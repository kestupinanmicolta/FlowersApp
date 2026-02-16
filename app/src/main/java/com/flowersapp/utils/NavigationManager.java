package com.flowersapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.flowersapp.data.model.Catalog;
import com.flowersapp.ui.CartActivity;
import com.flowersapp.ui.CatalogActivity;
import com.flowersapp.ui.ProductDetailActivity;

public class NavigationManager {

    public static void goToCatalog(Activity activity) {
        Intent intent = new Intent(activity, CatalogActivity.class);
        activity.startActivity(intent);
         activity.finish();
    }
    public static void goToCart(Activity activity) {
        Intent intent = new Intent(activity, CartActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
    public static void goToProductDetail(Context context, Catalog flower) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("flower_name", flower.getName());
        intent.putExtra("flower_price", flower.getPrice());
        intent.putExtra("flower_image", flower.getImage());
        context.startActivity(intent);
    }
}
