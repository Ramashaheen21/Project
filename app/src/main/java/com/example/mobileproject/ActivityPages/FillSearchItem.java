package com.example.mobileproject.ActivityPages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;

import java.util.List;

public abstract class FillSearchItem extends RecyclerView.Adapter<FillSearchItem.ViewHolder> {
    private List<String> items;

    public FillSearchItem(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searched_items_in_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);
        // Bind data to your item layout views here
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Define views from searched_items_in_list.xml here
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views here
        }
    }
}
