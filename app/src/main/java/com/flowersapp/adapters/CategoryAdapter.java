package com.flowersapp.adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flowersapp.R;
import com.flowersapp.data.model.Category;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final OnCategoryClickListener listener;
    private int selectedPosition = 0;
    private List<Category> categoryList;

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }
    public CategoryAdapter(List<Category> categoryList, OnCategoryClickListener listener) {
        this.categoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.btnCategoryItem.setText(category.getCategory());

        // LÓGICA DE SELECCIÓN VISUAL
        if (selectedPosition == position) {
            // Estilo seleccionado (Relleno rosa, texto blanco)
            holder.btnCategoryItem.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D81B60")));
            holder.btnCategoryItem.setTextColor(Color.WHITE);
            holder.btnCategoryItem.setStrokeWidth(0); // Quitar borde en modo seleccionado
        } else {
            // Estilo no seleccionado (Borde gris, texto gris)
            holder.btnCategoryItem.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
            holder.btnCategoryItem.setTextColor(Color.parseColor("#666666"));
            holder.btnCategoryItem.setStrokeWidth(2); // Mantener borde outlined
        }

        holder.btnCategoryItem.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos == RecyclerView.NO_POSITION || currentPos == selectedPosition) return;

            int previousPosition = selectedPosition;
            selectedPosition = currentPos;

            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);

            listener.onCategoryClick(category);
        });
    }
    public void updateCategories(List<Category> newList) {
        this.categoryList = newList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialButton btnCategoryItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCategoryItem = itemView.findViewById(R.id.btnCategoryItem);
        }
    }

    @Override
    public int getItemCount() {
        return categoryList != null ? categoryList.size() : 0;
    }
}
