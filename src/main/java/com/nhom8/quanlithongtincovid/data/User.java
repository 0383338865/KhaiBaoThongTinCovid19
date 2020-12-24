package com.nhom8.quanlithongtincovid.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//đánh dấu cho hệ thống nhận biết đây là 1 bảng trong CSDL
@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo
    private String hoTen;

    @ColumnInfo
    private String soDT;
    @ColumnInfo
    private String ngaySinh;
    @ColumnInfo
    private String taiKhoan;
    @ColumnInfo
    private String matKhau;
    @ColumnInfo
    private String diaChi;

    public User(String hoTen, String soDT, String ngaySinh, String taiKhoan, String matKhau, String diaChi) {
        this.hoTen = hoTen;
        this.soDT = soDT;
        this.ngaySinh = ngaySinh;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
