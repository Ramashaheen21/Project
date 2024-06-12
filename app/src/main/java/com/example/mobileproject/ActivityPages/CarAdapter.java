package com.example.mobileproject.ActivityPages;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private ArrayList<Coco> carList;
    private Context context;

    public CarAdapter(Context context, ArrayList<Coco> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(16, 16, 16, 16);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(8, 0, 8, 0); // Adding margin between items

        TextView tvBrandModel = new TextView(context);
        tvBrandModel.setId(View.generateViewId());
        tvBrandModel.setTextSize(20);
        tvBrandModel.setLayoutParams(params);
        layout.addView(tvBrandModel);

        TextView tvSeatNumber = new TextView(context);
        tvSeatNumber.setId(View.generateViewId());
        tvSeatNumber.setTextSize(20);
        tvSeatNumber.setLayoutParams(params);
        layout.addView(tvSeatNumber);

        TextView tvColor = new TextView(context);
        tvColor.setId(View.generateViewId());
        tvColor.setTextSize(20);
        tvColor.setLayoutParams(params);
        layout.addView(tvColor);

        TextView tvGearType = new TextView(context);
        tvGearType.setId(View.generateViewId());
        tvGearType.setTextSize(20);
        tvGearType.setLayoutParams(params);
        layout.addView(tvGearType);

        TextView tvPricePerDay = new TextView(context);
        tvPricePerDay.setId(View.generateViewId());
        tvPricePerDay.setTextSize(20);
        tvPricePerDay.setLayoutParams(params);
        layout.addView(tvPricePerDay);

        TextView tvYear = new TextView(context);
        tvYear.setId(View.generateViewId());
        tvYear.setTextSize(20);
        tvYear.setLayoutParams(params);
        layout.addView(tvYear);

        return new CarViewHolder(layout, tvBrandModel, tvSeatNumber, tvColor, tvGearType, tvPricePerDay, tvYear);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Coco currentCar = carList.get(position);
        holder.tvBrandModel.setText(currentCar.getBrandModel());
        holder.tvSeatNumber.setText(currentCar.getSeatNumber());
        holder.tvColor.setText(currentCar.getColor());
        holder.tvGearType.setText(currentCar.getGearType());
        holder.tvPricePerDay.setText(currentCar.getPricePerDay());
        holder.tvYear.setText(currentCar.getYear());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        public TextView tvBrandModel, tvSeatNumber, tvColor, tvGearType, tvPricePerDay, tvYear;

        public CarViewHolder(@NonNull LinearLayout itemView, TextView tvBrandModel, TextView tvSeatNumber, TextView tvColor, TextView tvGearType, TextView tvPricePerDay, TextView tvYear) {
            super(itemView);
            this.tvBrandModel = tvBrandModel;
            this.tvSeatNumber = tvSeatNumber;
            this.tvColor = tvColor;
            this.tvGearType = tvGearType;
            this.tvPricePerDay = tvPricePerDay;
            this.tvYear = tvYear;
        }
    }
}
