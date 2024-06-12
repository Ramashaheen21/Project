package com.example.mobileproject.ActivityPages;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    private String carId;
    private String carBrand;
    private String carModel;
    private int year;
    private int seats;
    private String gearType;
    private String color;
    private double pricePerDay;
    private String start,end;
    private double Total;

    public Car(String carModel, int year,String start, String end, double total) {

        this.carModel = carModel;
        this.year = year;
        this.start = start;
        this.end = end;
        this.Total = total;
    }

    // Constructor
    public Car(String model, int year) {
        this.carModel = model;
        this.year = year;
    }

    public Car(String carId, String carBrand, String carModel, int year, int seats, String gearType, String color, double pricePerDay) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.year = year;
        this.seats = seats;
        this.gearType = gearType;
        this.color = color;
        this.pricePerDay = pricePerDay;
    }

    // Parcelable implementation
    protected Car(Parcel in) {
        carId = in.readString();
        carBrand = in.readString();
        carModel = in.readString();
        year = in.readInt();
        seats = in.readInt();
        gearType = in.readString();
        color = in.readString();
        pricePerDay = in.readDouble();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(carId);
        parcel.writeString(carBrand);
        parcel.writeString(carModel);
        parcel.writeInt(year);
        parcel.writeInt(seats);
        parcel.writeString(gearType);
        parcel.writeString(color);
        parcel.writeDouble(pricePerDay);
    }

    // Getters and setters
    public String getModel() {
        return carModel;
    }

    public int getYear() {
        return year;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
