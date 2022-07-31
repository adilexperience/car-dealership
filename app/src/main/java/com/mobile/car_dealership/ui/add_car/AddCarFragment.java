package com.mobile.car_dealership.ui.add_car;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.mobile.car_dealership.database.DatabaseHelper;
import com.mobile.car_dealership.models.CarModel;
import com.mobile.searchplaces.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddCarFragment extends Fragment {
    Button fabSelectImage;
    ImageView ivSelectedImage;
    EditText etName, etDescription, etHP;
    Button btnAddCar;

    Bitmap bitmap;

    ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Do your code from onActivityResult

                    if(result.getData() != null && result.getResultCode() == RESULT_OK) {
                        InputStream inputStream = null;
                        try {
                            inputStream = getContext().getContentResolver().openInputStream(result.getData().getData());
                            bitmap = BitmapFactory.decodeStream(inputStream);
                            ivSelectedImage.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });

    public AddCarFragment() {
        // Required empty public constructor
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_place, container, false);

        fabSelectImage = view.findViewById(R.id.fab_select_image);
        ivSelectedImage = view.findViewById(R.id.iv_selected_image);
        etName = view.findViewById(R.id.et_name);
        etDescription = view.findViewById(R.id.et_description);
        etHP = view.findViewById(R.id.et_rating);
        btnAddCar = view.findViewById(R.id.btn_add_car);

        fabSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                Intent chooserIntent = Intent.createChooser(intent, "Select picture");
                mLauncher.launch(chooserIntent);
            }
        });

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim(), description = etDescription.getText().toString().trim(), rating = etHP.getText().toString().trim();
                if(name.isEmpty()) {
                    Toast.makeText(getContext(), "Enter car name", Toast.LENGTH_SHORT).show();
                }else if(description.isEmpty()) {
                    Toast.makeText(getContext(), "Enter car description", Toast.LENGTH_SHORT).show();
                }else if(rating.isEmpty()) {
                    Toast.makeText(getContext(), "Enter car rating", Toast.LENGTH_SHORT).show();
                }else {
                    double doubleHP = Double.parseDouble(rating);

                    if(bitmap == null) {
                        Toast.makeText(getContext(), "Select image", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    CarModel car = new CarModel(0, name, description, getBytes(bitmap), doubleHP);

                    // add to database now
                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                    databaseHelper.addCar(car);
                    Toast.makeText(getContext(), "Car added to dealership successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}