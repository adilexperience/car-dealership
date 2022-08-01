package com.mobile.car_dealership.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.car_dealership.models.CarModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    String carsTable = "Cars";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "CarsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + carsTable + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DESCRIPTION TEXT, HP REAL, IMAGE BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + carsTable);
    }

    public void addCar(CarModel place) {
        ContentValues values = new ContentValues();
        values.put("NAME", place.getName());
        values.put("DESCRIPTION", place.getDescription());
        values.put("HP", place.getHp());
        values.put("IMAGE", place.getImage());
        this.getWritableDatabase().insert(carsTable, null, values);
    }

    public ArrayList<CarModel> getSearchedCars(String name) {
        ArrayList<CarModel> cars = new ArrayList<CarModel>();
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT * FROM " + carsTable + " WHERE NAME='" + name + "'", null);

        while(cursor.moveToNext()) {
            CarModel car = new CarModel();
            car.setId(Integer.parseInt(cursor.getString(0)));
            car.setName(cursor.getString(1));
            car.setDescription(cursor.getString(2));
            car.setHp(Double.parseDouble(cursor.getString(3)));
            car.setImage(cursor.getBlob(4));

            cars.add(car);
        }

        return cars;
    }

    public ArrayList<CarModel> getAllCars() {
        ArrayList<CarModel> cars = new ArrayList<CarModel>();
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT * FROM " + carsTable, null);

        while(cursor.moveToNext()) {
            CarModel car = new CarModel();
            car.setId(Integer.parseInt(cursor.getString(0)));
            car.setName(cursor.getString(1));
            car.setDescription(cursor.getString(2));
            car.setHp(Double.parseDouble(cursor.getString(3)));
            car.setImage(cursor.getBlob(4));

            cars.add(car);
        }

        return cars;
    }

}
