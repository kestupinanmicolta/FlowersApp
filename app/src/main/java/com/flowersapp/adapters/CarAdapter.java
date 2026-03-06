package com.flowersapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flowersapp.R;
import com.flowersapp.data.model.Cart;

import java.text.DecimalFormat;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<Cart> cartList;
    private final OnCartChangeListener listener;

    // Interfaz para avisar a la Activity que el total cambió
    public interface OnCartChangeListener {
        void onUpdateTotal();
    }

    public CarAdapter(List<Cart> cartList, OnCartChangeListener listener) {
        this.cartList = cartList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart_product, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Cart cart = cartList.get(position);

        holder.tvCartProductName.setText(cart.getName());
        DecimalFormat formatter = new DecimalFormat("$ #,###");
        holder.tvCartProductPrice.setText("Precio: " + formatter.format(cart.getPrice()));
        holder.tvCartProductQty.setText(String.valueOf(cart.getCantidad()));
        holder.ivCartProduct.setImageResource(cart.getImage());

        // LÓGICA DE LOS BOTONES

        // Botón Eliminar
        holder.btnDeleteCart.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                cartList.remove(currentPos);
                notifyItemRemoved(currentPos);
                notifyItemRangeChanged(currentPos, cartList.size());
                listener.onUpdateTotal(); // Recalcular total en Activity
            }
        });

        // Botón Sumar (+)
        holder.btnPlusCart.setOnClickListener(v -> {
            cart.setCantidad(cart.getCantidad() + 1);
            notifyItemChanged(holder.getAdapterPosition());
            listener.onUpdateTotal(); // Recalcular total en Activity
        });

        // Botón Restar (-)
        holder.btnMinusCart.setOnClickListener(v -> {
            if (cart.getCantidad() > 1) {
                cart.setCantidad(cart.getCantidad() - 1);
                notifyItemChanged(holder.getAdapterPosition());
                listener.onUpdateTotal(); // Recalcular total en Activity
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList != null ? cartList.size() : 0;
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView tvCartProductName, tvCartProductPrice, tvCartProductQty;
        ImageView ivCartProduct;
        ImageButton btnDeleteCart, btnPlusCart, btnMinusCart;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCartProductName = itemView.findViewById(R.id.tvCartProductName);
            tvCartProductPrice = itemView.findViewById(R.id.tvCartProductPrice);
            tvCartProductQty = itemView.findViewById(R.id.tvCartProductQty);
            ivCartProduct = itemView.findViewById(R.id.ivCartProduct);
            btnDeleteCart = itemView.findViewById(R.id.btnDeleteCart);
            btnPlusCart = itemView.findViewById(R.id.btnPlusCart);
            btnMinusCart = itemView.findViewById(R.id.btnMinusCart);
        }
    }
}