package com.mobile.car_dealership.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.car_dealership.adapters.CarsAdapter;
import com.mobile.car_dealership.database.DatabaseHelper;
import com.mobile.car_dealership.models.CarModel;
import com.mobile.searchplaces.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvCars;

    private CarsAdapter carsAdapter;
    private ArrayList<CarModel> cars = new ArrayList<CarModel>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvCars = view.findViewById(R.id.rv_popular_cars);

        // get places from database and display
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        cars =  databaseHelper.getAllCars();

        carsAdapter = new CarsAdapter(getContext(), cars);

        rvCars.setHasFixedSize(true);
        rvCars.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCars.setAdapter(carsAdapter);

        return view;
    }
}