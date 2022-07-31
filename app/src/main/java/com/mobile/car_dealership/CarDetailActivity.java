package com.mobile.car_dealership;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.car_dealership.models.CarModel;
import com.mobile.searchplaces.R;

public class CarDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvName, tvDescription, tvRating;
    CarModel car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        car = (CarModel) getIntent().getSerializableExtra("car");


        imageView = findViewById(R.id.iv_car);
        tvName = findViewById(R.id.tv_name);
        tvDescription = findViewById(R.id.tv_description);
        tvRating = findViewById(R.id.tv_hp);


        imageView.setImageBitmap(getImage(car.getImage()));
        tvName.setText(car.getName());
        tvDescription.setText(car.getDescription());
        tvRating.setText("Horse Power of this car is: " + car.getHp());
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}