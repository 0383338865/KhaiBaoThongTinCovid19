package com.nhom8.quanlithongtincovid.suckhoecanhan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nhom8.quanlithongtincovid.R;
import com.nhom8.quanlithongtincovid.databinding.ActivitySucKhoeCaNhanBinding;
import com.nhom8.quanlithongtincovid.trangchu.TrangChuActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SucKhoeCaNhanActivity extends AppCompatActivity {
    private ActivitySucKhoeCaNhanBinding binding;
    private String hoTen = "";
    private String ngaySinh = "";
    private String cMT = "";
    private String diaChi = "";
    private String soDT = "";
    private String gioiTinh = "";
    private String bieuHien = "";
    private String coDiTuVungDichVeKhong = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_suc_khoe_ca_nhan);
        Intent intent = getIntent();
        hoTen = intent.getStringExtra("HoTen");
        ngaySinh = intent.getStringExtra("ngaySinh");
        cMT = intent.getStringExtra("cMT");
        diaChi = intent.getStringExtra("diaChi");
        soDT = intent.getStringExtra("soDT");
        gioiTinh = intent.getStringExtra("gioiTinh");
        bieuHien = intent.getStringExtra("bieuHien");
        coDiTuVungDichVeKhong = intent.getStringExtra("coDiTuVungDichVeKhong");

        binding.tvTen.setText(hoTen);
        binding.tvNgaySinh.setText(ngaySinh);

        //thiết lập ngày giờ khai báo cho nó
        setTime();
        setTinhTrangSucKhoe();
        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTinhTrang();
            }
        });

        binding.imageBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SucKhoeCaNhanActivity.this, TrangChuActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void shareTinhTrang() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, hoTen + " " +
                binding.tvTinhTrangSucKhoe.getText().toString());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void setTinhTrangSucKhoe() {
        if (bieuHien.contains("Sốt") || bieuHien.contains("Đau họng") || bieuHien.contains("Mệt mỏi")
                || bieuHien.contains("Khó thở") || bieuHien.contains("Ho")) {
            binding.tvTinhTrangSucKhoe.setText("Có dấu hiệu nhiễm COVID 19. Vui lòng đến trug tâm y tế gần nhất");
            binding.imageSucKhoe.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.virus));
        } else if (coDiTuVungDichVeKhong.compareTo("Có đi từ vùng dịch về") == 0) {
            binding.tvTinhTrangSucKhoe.setText("Bạn vui lòng đến cơ sở gần nhất để kiểm tra sức khỏe");
            binding.imageSucKhoe.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.virus));
        } else if (bieuHien.compareTo("Không có biểu hiện") == 0 && coDiTuVungDichVeKhong.compareTo("Không đi từ vùng dịch về") == 0) {
            binding.tvTinhTrangSucKhoe.setText("Khỏe mạnh");
            binding.imageSucKhoe.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.face_mask));
        }
    }

    private void setTime() {
        String date = "Calendar.getInstance().getTime()";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat spf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");
        try {
            Date newDate = spf.parse(date);
            binding.tvTime.setText(newDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}