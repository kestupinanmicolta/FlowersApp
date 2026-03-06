package com.flowersapp.adapters;

import static com.flowersapp.utils.NavigationManager.goToProductDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flowersapp.R;
import com.flowersapp.data.model.Catalog;
import com.flowersapp.data.model.User;

import java.text.DecimalFormat;
import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {
    public List<Catalog> catalogList;
    private User user;

    public CatalogAdapter(List<Catalog> catalogList, User user) {
        this.catalogList = catalogList;
        this.user=user;
    }

    @NonNull
    @Override
    public CatalogAdapter.CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flower, parent, false);
        return new CatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogAdapter.CatalogViewHolder holder, int position) {
        Catalog catalog = catalogList.get(position);
        holder.tvFlowerName.setText(catalog.getName());
        DecimalFormat formatter = new DecimalFormat("$ #,###");
        holder.tvFlowerPrice.setText(formatter.format(catalog.getPrice()));
        holder.ivFlower.setImageResource(catalog.getImage());
        holder.itemView.setOnClickListener(v -> {
            goToProductDetail(v.getContext(), catalog,user);
        });
    }

    @Override
    public int getItemCount() {
        return catalogList != null ? catalogList.size() : 0;
    }

    public static class CatalogViewHolder extends RecyclerView.ViewHolder {
        TextView tvFlowerName, tvFlowerPrice;
        ImageView ivFlower;

        public CatalogViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFlowerName = itemView.findViewById(R.id.tvFlowerName);
            tvFlowerPrice = itemView.findViewById(R.id.tvFlowerPrice);
            ivFlower = itemView.findViewById(R.id.ivFlower);
        }
    }

    public void updateList(List<Catalog> newList) {
        this.catalogList = newList; // 'listaFlores' debe ser el nombre de tu lista interna
        notifyDataSetChanged();
    }
}
