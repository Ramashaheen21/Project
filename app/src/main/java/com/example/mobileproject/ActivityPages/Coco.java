package com.example.mobileproject.ActivityPages;
import java.io.Serializable;
public class Coco implements  Serializable{
    private String id;
    private String brandModel;
    private String seatNumber;
    private String color;
    private String gearType;
    private String pricePerDay;
    private String year;

    // Constructor
    public Coco(String id, String brandModel, String seatNumber, String color, String gearType, String pricePerDay, String year) {
        this.id = id;
        this.brandModel = brandModel;
        this.seatNumber = seatNumber;
        this.color = color;
        this.gearType = gearType;
        this.pricePerDay = pricePerDay;
        this.year = year;
    }

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}


