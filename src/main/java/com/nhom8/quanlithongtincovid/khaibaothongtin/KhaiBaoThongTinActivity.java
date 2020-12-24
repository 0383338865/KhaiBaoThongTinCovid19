package com.nhom8.quanlithongtincovid.khaibaothongtin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nhom8.quanlithongtincovid.R;
import com.nhom8.quanlithongtincovid.databinding.ActivityKhaiBaoThongTinBinding;
import com.nhom8.quanlithongtincovid.suckhoecanhan.SucKhoeCaNhanActivity;

public class KhaiBaoThongTinActivity extends AppCompatActivity {

    private ActivityKhaiBaoThongTinBinding binding;

    private String hoTen="";
    private String ngaySinh="";
    private String cMT="";
    private String diaChi="";
    private String soDT="";
    private String gioiTinh="";
    private String bieuHien="Không có biểu hiện";
    private String coDiTuVungDichVeKhong="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_khai_bao_thong_tin);
        binding.btnKhaiBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoTen = binding.edtHoTenKbtn.getText().toString();
                ngaySinh = binding.edtNgaySinhKbtn.getText().toString();
                cMT = binding.edtCmtKbtn.getText().toString();
                diaChi = binding.edtDiaChiKbtn.getText().toString();
                soDT = binding.edtSoDtKbtn.getText().toString();
                int checkItemRadioClick = binding.rdgGioiTinh.getCheckedRadioButtonId();
                if (checkItemRadioClick == R.id.rdb_nam) {
                    gioiTinh = "Nam";
                } else if (checkItemRadioClick == R.id.rdb_nu) {
                    gioiTinh = "Nữ";
                }
                binding.rdgGioiTinh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int checkItemRadioClick = binding.rdgGioiTinh.getCheckedRadioButtonId();
                        if (checkItemRadioClick == R.id.rdb_nam) {
                            gioiTinh = "Nam";
                        } else if (checkItemRadioClick == R.id.rdb_nu) {
                            gioiTinh = "Nữ";
                        }
                    }
                });
                int checkItemRadioClick1 = binding.rdgCoDiTuVungDichKhong.getCheckedRadioButtonId();
                if (checkItemRadioClick1 == R.id.rdb_co) {
                    coDiTuVungDichVeKhong = "Có đi từ vùng dịch về";
                } else if (checkItemRadioClick1 == R.id.rdb_khong) {
                    coDiTuVungDichVeKhong = "Không đi từ vùng dịch về";
                }
                binding.rdgCoDiTuVungDichKhong.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int checkItemRadioClick1 = binding.rdgCoDiTuVungDichKhong.getCheckedRadioButtonId();
                        if (checkItemRadioClick1 == R.id.rdb_co) {
                            coDiTuVungDichVeKhong = "Có đi từ vùng dịch về";
                        } else if (checkItemRadioClick1 == R.id.rdb_khong) {
                            coDiTuVungDichVeKhong = "Không đi từ vùng dịch về";
                        }
                    }
                });

                if (binding.cbHo.isChecked()) {
                    bieuHien += "Sốt";
                } else if (binding.cbDauHong.isChecked()) {
                    bieuHien += "Đau họng";
                } else if (binding.cbKhongCoTrieuChung.isChecked()) {
                    bieuHien += "Không có biểu hiện";
                } else if (binding.cbMetMoi.isChecked()) {
                    bieuHien += "Mệt mỏi";
                } else if (binding.cbKhoTho.isChecked()) {
                    bieuHien += "Khó thở";
                } else if (binding.cbHo.isChecked()) {
                    bieuHien += "Ho";
                }

                if (hoTen.equals("") || ngaySinh.equals("") || gioiTinh.equals("")
                        || diaChi.equals("") || bieuHien.equals("") ||
                        coDiTuVungDichVeKhong.equals("") ||
                        soDT.equals("")) {
                    Toast.makeText(KhaiBaoThongTinActivity.this, "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(KhaiBaoThongTinActivity.this, SucKhoeCaNhanActivity.class);
                intent.putExtra("HoTen", hoTen);
                intent.putExtra("ngaySinh", ngaySinh);
                intent.putExtra("cMT", cMT);
                intent.putExtra("diaChi", diaChi);
                intent.putExtra("soDT", soDT);
                intent.putExtra("gioiTinh", gioiTinh);
                intent.putExtra("bieuHien", bieuHien);
                intent.putExtra("coDiTuVungDichVeKhong", coDiTuVungDichVeKhong);
                startActivity(intent);
                finish();

            }
        });

        binding.btnHuyBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtSoDtKbtn.setText("");
                binding.edtCmtKbtn.setText("");
                binding.edtDiaChiKbtn.setText("");
                binding.edtNgaySinhKbtn.setText("");
                binding.edtHoTenKbtn.setText("");
                binding.rdbNam.setChecked(true);
                binding.cbHo.setChecked(false);
                binding.cbKhoTho.setChecked(false);
                binding.cbMetMoi.setChecked(false);
                binding.cbKhongCoTrieuChung.setChecked(false);
                binding.cbDauHong.setChecked(false);
                binding.cbSot.setChecked(false);
                binding.rdbKhong.setChecked(true);
            }
        });
    }
}