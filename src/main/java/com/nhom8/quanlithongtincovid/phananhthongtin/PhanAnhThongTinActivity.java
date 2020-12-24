package com.nhom8.quanlithongtincovid.phananhthongtin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.ImageViewBindingAdapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nhom8.quanlithongtincovid.R;
import com.nhom8.quanlithongtincovid.databinding.ActivityPhanAnhThongTinBinding;
import com.nhom8.quanlithongtincovid.khaibaothongtin.KhaiBaoThongTinActivity;
import com.nhom8.quanlithongtincovid.suckhoecanhan.SucKhoeCaNhanActivity;
import com.nhom8.quanlithongtincovid.trangchu.TrangChuActivity;

import java.util.Calendar;

public class PhanAnhThongTinActivity extends AppCompatActivity {

    private ActivityPhanAnhThongTinBinding binding;

    private String phanAnhTruongHop = "";
    private String diaDiem = "";
    private String time="";

    private int year;
    private int month;
    private int day;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_phan_anh_thong_tin);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        binding.edtDate.setText(day+"/"+month+"/"+year+"");
        binding.tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19009095"));
                startActivity(intent);
            }
        });

        thoiGianPhanAnh();
        binding.btnKhaiBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                truongHopPhanAnh();
                diaDiem();
                time=binding.edtDate.getText().toString();
                if (!binding.cbNghiMacBenh.isChecked()
                        &&!binding.cbNghiMacBenh.isChecked() &&!binding.cbNghiMacBenh.isChecked()
                        ||diaDiem.equals("")){
                    Toast.makeText(PhanAnhThongTinActivity.this, "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(PhanAnhThongTinActivity.this);
                builder.setMessage("Thông tin của bạn đã được phản ánh thành công. Vào ngày "+time+", "+phanAnhTruongHop+" tại "+diaDiem)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               Intent intent=new Intent(PhanAnhThongTinActivity.this, TrangChuActivity.class);
                               startActivity(intent);
                               finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        binding.btnHuyBo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.cbTiepXuc.setChecked(false);
                binding.cbDiTuVungDichVe.setChecked(false);
                binding.cbNghiMacBenh.setChecked(false);
                binding.edtDiaChi.setText("");
                binding.edtDate.setText(day+"/"+month+"/"+year+"");

            }
        });

    }

    private void diaDiem() {
        diaDiem = binding.edtDiaChi.getText().toString();
    }

    @SuppressLint("SetTextI18n")
    private void thoiGianPhanAnh() {


        binding.btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        binding.edtDate.setText(dayOfMonth+"/"+(month+1)+"/"+year+"");

                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        PhanAnhThongTinActivity.this, callback,year,month,day);
                datePickerDialog.show();
            }
        });

    }

    private void truongHopPhanAnh() {
        checkThongTinPhanAnh();

        binding.cbNghiMacBenh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkThongTinPhanAnh();
            }
        });
        binding.cbDiTuVungDichVe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               checkThongTinPhanAnh();
            }
        });
        binding.cbTiepXuc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkThongTinPhanAnh();
            }
        });
    }

    private void checkThongTinPhanAnh() {
        if (binding.cbNghiMacBenh.isChecked() && !binding.cbDiTuVungDichVe.isChecked()
                &&!binding.cbTiepXuc.isChecked()) {
            phanAnhTruongHop = "Có trường hợp nghi mắc bệnh";
        } else if (!binding.cbNghiMacBenh.isChecked() && binding.cbDiTuVungDichVe.isChecked()
                &&!binding.cbTiepXuc.isChecked()) {
            phanAnhTruongHop = "Có trường hợp đi từ vùng dịch về";
        } else if (!binding.cbNghiMacBenh.isChecked() && !binding.cbDiTuVungDichVe.isChecked()
                &&binding.cbTiepXuc.isChecked()) {
            phanAnhTruongHop = "Có trường hợp tiếp xúc với người nghi ngờ mắc bệnh hoặc đi từ vùng dịch về";
        }else if (binding.cbNghiMacBenh.isChecked() && binding.cbDiTuVungDichVe.isChecked()
                &&!binding.cbTiepXuc.isChecked()) {
            phanAnhTruongHop = "Có trường hợp nghi ngờ mắc bệnh và đi từ vùng dịch về";
        }else if (binding.cbNghiMacBenh.isChecked() && !binding.cbDiTuVungDichVe.isChecked()
                &&binding.cbTiepXuc.isChecked()) {
            phanAnhTruongHop = "Có trường hợp nghi ngờ mắc bệnh và tiếp xúc với người nghi ngờ mắc bệnh hoặc đi từ vùng dịch về";
        }else if (!binding.cbNghiMacBenh.isChecked() && binding.cbDiTuVungDichVe.isChecked()
                &&binding.cbTiepXuc.isChecked()) {
            phanAnhTruongHop = "Có trường hợp tiếp xúc với người nghi ngờ mắc bệnh hoặc đi từ vùng dịch về";
        }else if (binding.cbNghiMacBenh.isChecked() && binding.cbDiTuVungDichVe.isChecked()
                &&binding.cbTiepXuc.isChecked()) {
            phanAnhTruongHop = "Có trường hợp nguy hiểm nghi ngờ nhiễm COVID 19 ";
        }
    }


}