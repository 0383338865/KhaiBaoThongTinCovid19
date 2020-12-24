package com.nhom8.quanlithongtincovid.dangnhap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.nhom8.quanlithongtincovid.R;
import com.nhom8.quanlithongtincovid.dangki.DangKiActivity;
import com.nhom8.quanlithongtincovid.data.User;
import com.nhom8.quanlithongtincovid.database.Appdatabase;
import com.nhom8.quanlithongtincovid.databinding.ActivityDangNhapBinding;
import com.nhom8.quanlithongtincovid.trangchu.TrangChuActivity;

import java.util.LinkedList;
import java.util.List;


public class DangNhapActivity extends AppCompatActivity {

    private final static int REQUEST_COST = 1;

    private ActivityDangNhapBinding binding;
    SharedPreferences sharedPreferences;
    private String tk=null;
    private String mk=null;
    private String check=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dang_nhap);
        sharedPreferences = getSharedPreferences("Login4", MODE_PRIVATE);
        check=sharedPreferences.getString("Check","1");
        if (check.compareTo("true")==0) {
            binding.edtTaiKhoanScreen.setText(sharedPreferences.getString("TaiKhoan", "TaiKhoan"));
            binding.edtPasswordDangNhapScreen.setText(sharedPreferences.getString("MatKhau", "MatKhau"));
            binding.checkboxPassword.setChecked(true);
        }
        //thiết lập sự kiện cho nút đăng kí
        binding.btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKiActivity.class);
                startActivityForResult(intent, REQUEST_COST);
            }
        });

        //thiết lập sự kiện cho nút đăng nhập
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taiKhoan = binding.edtTaiKhoanScreen.getText().toString();
                String matKhau = binding.edtPasswordDangNhapScreen.getText().toString();
                if (taiKhoan.equals("") || matKhau.equals("")) {
                    Toast.makeText(DangNhapActivity.this, "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                int check = 0;
                List<User> data = Appdatabase.getInMemoryDatabase(getApplicationContext()).userDao().getAll();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getTaiKhoan().compareTo(taiKhoan) == 0 && data.get(i).getMatKhau().compareTo(matKhau)!= 0) {
                        check = 1;
                        break;
                    } else if (data.get(i).getTaiKhoan().compareTo(taiKhoan) == 0
                            && data.get(i).getMatKhau().compareTo(matKhau)== 0) {
                        check = 2;
                        break;
                    }
                }
                if (check == 1) {
                    Toast.makeText(DangNhapActivity.this
                            , "Thông tin mật khẩu bạn nhập chưa đúng,vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                    binding.edtPasswordDangNhapScreen.requestFocus();
                    binding.edtPasswordDangNhapScreen.setText("");
                } else if (check == 2) {
                    Intent intent = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(DangNhapActivity.this
                            , "Thông tin tài khoản bạn nhập chưa đúng,vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                    binding.edtTaiKhoanScreen.setText("");
                    binding.edtTaiKhoanScreen.requestFocus();
                    binding.edtPasswordDangNhapScreen.setText("");
                }

            }
        });

        //thiết lập sự kiện thay đổi của check box
        binding.checkboxPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //sharedpreferences Là một cách lưu trữ dữ liệu nhỏ, đơn giản trong
                    // android dưới dạng file xml với những cặp key - value
                    //- Lưu trữ theo ứng dụng của người dùng cài đặt, nên khi gỡ ứng dụng dữ liệu sẽ mất theo.
                    //- Dùng khi lưu trữ tiến trình progressbar, hoặc là username, password.
                    // khi người dùng ấn vào nút ghi nhớ đăng nhập thì lần sau khi vào nó sẽ điền luôn.
                    //Khởi tạo kho lưu trữ data với name là login
                    //xác nhận đã hoàn thành việc lưu trữ
                    check="true";
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    tk=binding.edtTaiKhoanScreen.getText().toString();
                    mk=binding.edtPasswordDangNhapScreen.getText().toString();
                    editor.putString("TaiKhoan",tk);
                    editor.putString("MatKhau",mk);
                    editor.putString("Check",check);
                    // Save.
                    editor.apply();
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_COST) {
            if (resultCode == RESULT_OK && data != null) {
                String taiKhoan = data.getStringExtra("TaiKhoan");
                String matKhau = data.getStringExtra("MatKhau");
                binding.edtTaiKhoanScreen.setText(taiKhoan);
                binding.edtPasswordDangNhapScreen.setText(matKhau);
            }
        }
    }
}