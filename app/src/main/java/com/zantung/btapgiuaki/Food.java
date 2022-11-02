package com.zantung.btapgiuaki;

import java.io.Serializable;

public class Food implements Serializable {
    private String id, tenFood;
    private String Hinh;
    private double gia;

    public Food() {

    }

    public Food(String id, String tenFood, String hinh, double gia) {
        this.id = id;
        this.tenFood = tenFood;
        Hinh = hinh;
        this.gia = gia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenFood() {
        return tenFood;
    }

    public void setTenFood(String tenFood) {
        this.tenFood = tenFood;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
}
