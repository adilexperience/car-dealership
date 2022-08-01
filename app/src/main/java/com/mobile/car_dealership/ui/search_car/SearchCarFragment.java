package com.mobile.car_dealership.ui.search_car;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.mobile.car_dealership.adapters.CarsAdapter;
import com.mobile.car_dealership.database.DatabaseHelper;
import com.mobile.car_dealership.models.CarModel;
import com.mobile.car_dealership.R;

import java.util.ArrayList;

public class SearchCarFragment extends Fragment {
    EditText etSearch;
    ImageView ivSearch;

    RecyclerView rvSearch;
    private CarsAdapter carsAdapter;
    private ArrayList<CarModel> places = new ArrayList<CarModel>();
    public SearchCarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_place, container, false);

        etSearch = view.findViewById(R.id.et_search);
        ivSearch = view.findViewById(R.id.iv_search);
        rvSearch = view.findViewById(R.id.rv_search_cars);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etSearch.getText().toString().trim();

                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                places = databaseHelper.getSearchedCars(name);

                carsAdapter = new CarsAdapter(getContext(), places);

                rvSearch.setHasFixedSize(true);
                rvSearch.setLayoutManager(new LinearLayoutManager(getContext()));

                rvSearch.setAdapter(carsAdapter);
            }
        });

        return view;
    }
}