package com.nhom8.quanlithongtincovid.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nhom8.quanlithongtincovid.data.User;

import java.util.List;

//Gọi ra các phương thức với các câu lệnh truy vấn trong SQL
@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT Count(*) FROM User")
    int getCountItem();

    @Query("DELETE FROM User")
    void deleteAll();

    @Insert
    void insertUser(User... user);

    @Delete
    void deleteUser(User... user);

    @Update
    void update(User user);
}
