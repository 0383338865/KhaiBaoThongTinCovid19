package com.nhom8.quanlithongtincovid.dangki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nhom8.quanlithongtincovid.R;
import com.nhom8.quanlithongtincovid.data.User;
import com.nhom8.quanlithongtincovid.database.Appdatabase;
import com.nhom8.quanlithongtincovid.databinding.ActivityDangKiBinding;

public class DangKiActivity extends AppCompatActivity {
    private ActivityDangKiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dang_ki);

        binding.btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = binding.edtHoTen.getText().toString();
                String taiKhoan = binding.edtTaiKhoan.getText().toString();
                String matKhau = binding.edtPassword.getText().toString();
                String ngaySinh = binding.edtNgaySinh.getText().toString();
                String soDT = binding.edtSoDienThoai.getText().toString();
                String diaChi = binding.edtDiaChi.getText().toString();
                String matKhauAgain = binding.edtPasswordAgain.getText().toString();


                if (hoTen.equals("") || taiKhoan.equals("")
                        || matKhau.equals("") || ngaySinh.equals("")
                        || soDT.equals("") || diaChi.equals("") || matKhauAgain.equals("")) {
                    Toast.makeText(DangKiActivity.this, "Bạn cần nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (matKhau.compareTo(matKhauAgain) != 0) {
                    Toast.makeText(DangKiActivity.this, "Mật khẩu bạn nhập không khớp, vui lòng nhập lại"
                            , Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(hoTen, soDT, ngaySinh, taiKhoan, matKhau, diaChi);
                for (int i = 0; i < Appdatabase.getInMemoryDatabase(DangKiActivity.this).userDao().getCountItem(); i++) {
                    if (Appdatabase.getInMemoryDatabase(DangKiActivity.this).userDao().getAll().get(i).getTaiKhoan().toUpperCase()
                            .compareTo(user.getTaiKhoan().toUpperCase()) == 0) {
                        Toast.makeText(DangKiActivity.this
                                , "Tài khoản đã được sử dụng, vui lòng nhập tài khoản khác", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                int a=Appdatabase.getInMemoryDatabase(DangKiActivity.this).userDao().getCountItem();
                Appdatabase.getInMemoryDatabase(DangKiActivity.this).userDao().insertUser(user);
                int b=Appdatabase.getInMemoryDatabase(DangKiActivity.this).userDao().getCountItem();
                Intent intent = new Intent();
                intent.putExtra("TaiKhoan", taiKhoan);
                intent.putExtra("MatKhau", matKhau);
                setResult(RESULT_OK, intent);
                Toast.makeText(DangKiActivity.this, "Bạn đã đăng kí thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}