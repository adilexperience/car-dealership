package com.mobile.car_dealership.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.car_dealership.CarDetailActivity;
import com.mobile.car_dealership.models.CarModel;
import com.mobile.car_dealership.R;

import java.util.ArrayList;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {
    Context mContext;
    ArrayList<CarModel> cars;

    public CarsAdapter() {
    }

    public CarsAdapter(Context mContext, ArrayList<CarModel> cars) {
        this.mContext = mContext;
        this.cars = cars;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.car, parent, false);
        return new CarViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsAdapter.CarViewHolder holder, int position) {
        final CarModel carModel = cars.get(position);

        holder.mTVName.setText(carModel.getName());
        holder.mTVDescription.setText(carModel.getDescription());
        holder.mTVHP.setText("Horse Power of this car is: " + carModel.getHp());

        holder.mIVCar.setImageBitmap(getImage(carModel.getImage()));

        holder.cvCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CarDetailActivity.class);
                intent.putExtra("car", carModel);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        ImageView mIVCar;
        TextView mTVName, mTVDescription, mTVHP;
        CardView cvCar;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            mIVCar = itemView.findViewById(R.id.iv_car_item);
            mTVName = itemView.findViewById(R.id.tv_name);
            mTVDescription = itemView.findViewById(R.id.tv_description);
            mTVHP = itemView.findViewById(R.id.tv_hp);
            cvCar = itemView.findViewById(R.id.cv_car);
        }

    }

}
