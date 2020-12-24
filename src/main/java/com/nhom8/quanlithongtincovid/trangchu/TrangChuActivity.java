package com.nhom8.quanlithongtincovid.trangchu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nhom8.quanlithongtincovid.R;
import com.nhom8.quanlithongtincovid.databinding.ActivityTrangChuBinding;
import com.nhom8.quanlithongtincovid.khaibaothongtin.KhaiBaoThongTinActivity;
import com.nhom8.quanlithongtincovid.phananhthongtin.PhanAnhThongTinActivity;
import com.nhom8.quanlithongtincovid.xemchitiet.ThongTinChiTietActivity;

public class TrangChuActivity extends AppCompatActivity {

    private ActivityTrangChuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trang_chu);

        //set sự kiện chuyển màn hình sang màn hình khai báo thông tin cá nhân
        binding.btnKhaiBaoTuNguyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrangChuActivity.this, KhaiBaoThongTinActivity.class);
                startActivity(intent);
            }
        });

        binding.btnPhanAnhThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrangChuActivity.this, PhanAnhThongTinActivity.class);
                startActivity(intent);
            }
        });

        binding.tvXemChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrangChuActivity.this, ThongTinChiTietActivity.class);
                startActivity(intent);
            }
        });
    }
}