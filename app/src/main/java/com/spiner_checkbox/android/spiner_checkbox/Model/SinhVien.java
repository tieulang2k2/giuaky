package com.spiner_checkbox.android.spiner_checkbox.Model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    String ma;
    String id;
    String name;
    String sex;
    String job;
    String birhday;

    public SinhVien() {
    }

    public SinhVien(String ma, String id, String name, String sex, String job, String birhday) {
        this.ma = ma;
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.job = job;
        this.birhday = birhday;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirhday() {
        return birhday;
    }

    public void setBirhday(String birhday) {
        this.birhday = birhday;
    }
}
