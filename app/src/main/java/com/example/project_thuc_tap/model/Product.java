package com.example.project_thuc_tap.model;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    int ID;
    String TenSP;
    Integer GiaSp;
    String HinhAnhSp;
    String MotaSP;
    int IDloaiSP;
    int rate;

    public Product(int ID, String tenSP, Integer giaSp, String hinhAnhSp, String motaSP, int IDloaiSP, int rate) {
        this.ID = ID;
        this.TenSP = tenSP;
        this.GiaSp = giaSp;
        this.HinhAnhSp = hinhAnhSp;
        this.MotaSP = motaSP;
        this.IDloaiSP = IDloaiSP;
        this.rate = rate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public Integer getGiaSp() {
        return GiaSp;
    }

    public void setGiaSp(Integer giaSp) {
        GiaSp = giaSp;
    }

    public String getHinhAnhSp() {
        return HinhAnhSp;
    }

    public void setHinhAnhSp(String hinhAnhSp) {
        HinhAnhSp = hinhAnhSp;
    }

    public String getMotaSP() {
        return MotaSP;
    }

    public void setMotaSP(String motaSP) {
        MotaSP = motaSP;
    }

    public int getIDloaiSP() {
        return IDloaiSP;
    }

    public void setIDloaiSP(int IDloaiSP) {
        this.IDloaiSP = IDloaiSP;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}


