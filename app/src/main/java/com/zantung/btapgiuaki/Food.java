package com.zantung.btapgiuaki;

import java.io.Serializable;

public class Food implements Serializable {
    private String tenFood;
    private int Hinh;
    private double gia;

    public Food(String tenFood, int hinh, double gia) {
        this.tenFood = tenFood;
        Hinh = hinh;
        this.gia = gia;
    }

    public String getTenFood() {
        return tenFood;
    }

    public void setTenFood(String tenFood) {
        this.tenFood = tenFood;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
}
