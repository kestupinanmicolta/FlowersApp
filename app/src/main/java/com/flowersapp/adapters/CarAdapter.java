package com.flowersapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flowersapp.R;
import com.flowersapp.data.model.Car;

import java.text.DecimalFormat;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    public List<Car> carList;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart_product, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.tvCartProductName.setText(car.getName());
        DecimalFormat formatter = new DecimalFormat("Precio: $ #,###");
        holder.tvCartProductPrice.setText(formatter.format(car.getPrice()));
        holder.tvCartProductQty.setText("Cantidad: " + car.getCantidad());
        holder.ivCartProduct.setImageResource(car.getImage());
    }

    @Override
    public int getItemCount() {
        return carList != null ? carList.size() : 0;
    }
    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView tvCartProductName, tvCartProductPrice, tvCartProductQty;
        ImageView ivCartProduct;
        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCartProductName=itemView.findViewById(R.id.tvCartProductName);
            tvCartProductPrice=itemView.findViewById(R.id.tvCartProductPrice);
            tvCartProductQty=itemView.findViewById(R.id.tvCartProductQty);
            ivCartProduct=itemView.findViewById(R.id.ivCartProduct);
        }
    }
}
