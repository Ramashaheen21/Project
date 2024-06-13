package com.example.mobileproject.ActivityPages;

public class images {

    private String brand , model,id,images;

    public images(String brand, String model, String id, String images) {
        this.brand = brand;
        this.model = model;
        this.id = id;
        this.images = images;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
